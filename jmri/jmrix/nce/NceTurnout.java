// NceTurnout.java

package jmri.jmrix.nce;

import jmri.AbstractTurnout;
import jmri.NmraPacket;
import jmri.Turnout;
import jmri.PushbuttonPacket;

/**
 * Implement a Turnout via NCE communications.
 * <P>
 * This object doesn't listen to the NCE communications.  This is because
 * it should be the only object that is sending messages for this turnout;
 * more than one Turnout object pointing to a single device is not allowed.
 *
 * @author	Bob Jacobsen Copyright (C) 2001
 * @author Daniel Boudreau (C) 2007
 * @version	$Revision: 1.26 $
 */
public class NceTurnout extends AbstractTurnout {

    final String prefix = "NT";

    /**
     * NCE turnouts use the NMRA number (0-2044) as their numerical identification.
     */

    public NceTurnout(int number) {
    	super("NT"+number);
    	_number = number;

    	// At construction, register for messages
    	
    	numNtTurnouts++;

    	// dBoudreau update feedback modes
    	
    	if (NceMessage.getCommandOptions() >= NceMessage.OPTION_2006) {

    		if (modeNames == null) {

    			if (_validFeedbackNames.length != _validFeedbackModes.length)
    				log.error("int and string feedback arrays different length");
    			modeNames  = new String[_validFeedbackNames.length+1];
    			modeValues = new int[_validFeedbackNames.length+1];
    			for (int i = 0; i<_validFeedbackNames.length; i++) {
    				modeNames[i] = _validFeedbackNames[i];
    				modeValues[i] = _validFeedbackModes[i];
    			}
    			modeNames[_validFeedbackNames.length] = "MONITORING";
    			modeValues[_validFeedbackNames.length] = MONITORING;
    		}
    		_validFeedbackTypes |= MONITORING; 
    		_validFeedbackNames = modeNames;
    		_validFeedbackModes = modeValues;
    	}
    	_enableCabLockout = true;
    	_enablePushButtonLockout = true;
    }
    static String[] modeNames = null;
    static int[] modeValues = null;
    private static int numNtTurnouts = 0;
    
    public int getNumber() { return _number; }
    
    public static int getNumNtTurnouts() {return numNtTurnouts;};
    
      
     // Handle a request to change state by sending a turnout command
    protected void forwardCommandChangeToLayout(int s) {
        // implementing classes will typically have a function/listener to get
        // updates from the layout, which will then call
        //		public void firePropertyChange(String propertyName,
        //										Object oldValue,
        //										Object newValue)
        // _once_ if anything has changed state (or set the commanded state directly)

        // sort out states
        if ( (s & Turnout.CLOSED) > 0) {
            // first look for the double case, which we can't handle
            if ( (s & Turnout.THROWN) > 0) {
                // this is the disaster case!
                log.error("Cannot command both CLOSED and THROWN "+s);
                return;
            } else {
                // send a CLOSED command
                sendMessage(true^getInverted());
            }
        } else {
            // send a THROWN command
            sendMessage(false^getInverted());
        }
    }
    
    /**
     * Send a message to the layout to lock or unlock the turnout pushbuttons
     * if true, pushbutton lockout enabled
     */
    protected void turnoutPushbuttonLockout(boolean pushButtonLockout) {
		if (log.isDebugEnabled())
			log.debug("Send command to "
					+ (pushButtonLockout ? "Lock" : "Unlock")
					+ " Pushbutton NT" + _number);
		
		byte[] bl = PushbuttonPacket.pushbuttonPkt(prefix, _number, pushButtonLockout);
		NceMessage m = NceMessage.sendPacketMessage(bl);
		NceTrafficController.instance().sendNceMessage(m, null);
	}

    public void dispose() {}  // no connections need to be broken

    // data members
    int _number;   // turnout number
    
    /**
     * Set the turnout known state to reflect what's been observed
     * from the command station polling. A change there means that
     * somebody commanded a state change (e.g. somebody holding a 
     * throttle), and that command has already taken effect.
     * Hence we use "newCommandedState" to indicate it's taken place.
     * Must be followed by "newKnownState" to complete the turnout
     * action. 
     *
     * @param state Observed state, updated state from command station
     */
    synchronized void setCommandedStateFromCS(int state) {
		if ((getFeedbackMode() != MONITORING))
			return;
		
		newCommandedState(state);
	}
    
    /**
     * Set the turnout known state to reflect what's been observed
     * from the command station polling. A change there means that
     * somebody commanded a state change (e.g. somebody holding a 
     * throttle), and that command has already taken effect.
     * Hence we use "newKnownState" to indicate it's taken place.
     * <P>
     * @param state Observed state, updated state from command station
     */
    synchronized void setKnownStateFromCS(int state) {
		if ((getFeedbackMode() != MONITORING))
			return;
	
		newKnownState(state);
	}
    
    /**
     * NCE turnouts can be inverted
     */
    public boolean canInvert(){return true;}
    
    /**
     * NCE turnouts support two types of lockouts, pushbutton and cab.
     * Cab lockout requires the feedback mode to be Monitoring 
     */
    public boolean canLock(int turnoutLockout) {
		if ((turnoutLockout & PUSHBUTTONLOCKOUT) > 0 && _enablePushButtonLockout)
			return true;
		if ((turnoutLockout & CABLOCKOUT) > 0
				&& getFeedbackMode() == MONITORING && _enableCabLockout) {
			return true;
		} else
			return false;
	}
    
    /**
     * Control which turnout locks are enabled
     */
    public void enableLockOperation(int turnoutLockout, boolean enabled) {
		if ((turnoutLockout & CABLOCKOUT) > 0)
			if (enabled)
				_enableCabLockout = true;
			else {
				// unlock cab before disabling
				_cabLockout = false;
				_enableCabLockout = false;
				// pushbutton lockout has to be enabled if cab lockout is disabled
				_enablePushButtonLockout = true;
			}

		if ((turnoutLockout & PUSHBUTTONLOCKOUT) > 0)
			if (enabled) {
				_enablePushButtonLockout = true;
			} else {
				// only time we can disable pushbuttons is if we can lockout cabs
				if (getFeedbackMode() != MONITORING)
					return; 
				// pushbutton lockout has to be enabled if cab lockout is disabled
				if (_enableCabLockout)
				_enablePushButtonLockout = false;
			}
	}
 
    
    protected void sendMessage(boolean closed) {
        // get the packet
    	// dBoudreau  Added support for new accessory binary command
 
    	if (NceMessage.getCommandOptions() >= NceMessage.OPTION_2006) {
    		
    		byte [] bl = NceBinaryCommand.accDecoder(_number, closed);
    		
    		if (log.isDebugEnabled()) log.debug("Command: "
                    						+Integer.toHexString(0xFF & bl[0])
                    						+" "+Integer.toHexString(0xFF & bl[1])
                    						+" "+Integer.toHexString(0xFF & bl[2])
                    						+" "+Integer.toHexString(0xFF & bl[3])
                    						+" "+Integer.toHexString(0xFF & bl[4]));
    		
    		NceMessage m = NceMessage.createBinaryMessage(bl);

    		NceTrafficController.instance().sendNceMessage(m, null);

    	
    	} else {
    		    	
    		byte[] bl = NmraPacket.accDecoderPkt(_number, closed);       
    	
    		if (log.isDebugEnabled()) log.debug("packet: "
                                            +Integer.toHexString(0xFF & bl[0])
                                            +" "+Integer.toHexString(0xFF & bl[1])
                                            +" "+Integer.toHexString(0xFF & bl[2]));
    	
    		NceMessage m = NceMessage.sendPacketMessage(bl);

    		NceTrafficController.instance().sendNceMessage(m, null);
    	}
    }
    
    
 
    static org.apache.log4j.Category log = org.apache.log4j.Category.getInstance(NceTurnout.class.getName());
}

/* @(#)NceTurnout.java */


