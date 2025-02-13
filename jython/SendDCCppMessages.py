# Sending various messages to the DCCpp connection. 
#   Useful for intializing turnout definitions, power state, etc.
#   adapted from SendReceiveLocoNetPeerXfrMessage.py 

import jmri
import java

# create a minimal listener class needed to call sendDCCppMessage()
class PeerListener (jmri.jmrix.dccpp.DCCppListener) :
    def message(self, msg) :
        return

# function to send a string message to the DCCpp connection
def send(strMessage) :
    m = jmri.jmrix.dccpp.DCCppMessage.makeMessage(strMessage)
    tc.sendDCCppMessage(m, dl)

# get the DCCpp connection stuff once
dc = jmri.InstanceManager.getList(jmri.jmrix.dccpp.DCCppSystemConnectionMemo).get(0);
tc = dc.getDCCppTrafficController()
dl = tc.addDCCppListener(0xFF,PeerListener())
    

#-----------------------------------------------------------
# list of messages to send, examples are from DCC-EX 3.1.7+
send("T 22 SERVO 122 410 205 1") # Add Turnout Servo: ID:24, Pin:100, ThrownPos:410, ClosedPos:205, Profile:1
send("T 23 SERVO 123 410 205 2") # Add Turnout Servo: ID:24, Pin:100, ThrownPos:410, ClosedPos:205, Profile:2
send("T 24 SERVO 124 410 205 3") # Add Turnout Servo: ID:24, Pin:100, ThrownPos:410, ClosedPos:205, Profile:3
send("T 24 SERVO 124 400 200 3") # test updating with new settings
send("T 25 VPIN 50")    # Add Turnout Vpin: ID:25, Pin:50
send("T 26 VPIN 164")   # Add Turnout Vpin: ID:26, Pin:164   
send("T 27 DCC 27 1")   # Add Turnout DCC: ID:27, Address:27, Subaddr:1
send("T 28 DCC 28 0")   # Add Turnout DCC: ID:28, Address:28, Subaddr:0
send("T 29 DCC 29 1")   # Add Turnout DCC: ID:29, Address:29, Subaddr:1
send("Z 181 181 1")     # Add Output: ID: 181, Pin: 181, IFlag: 1
send("Z 180 180 0")     # Add Output: ID: 180, Pin: 180, IFlag: 0
send("S 111 111 0")     # Add Sensor: ID: 111, Pin: 111, Pullup: NO PULLUP
send("S 112 112 0")     # Add Sensor: ID: 112, Pin: 112, Pullup: NO PULLUP
send("T")               # request list of Turnout definitions
send("S")               # request list of Sensor definitions
send("Z")               # request list of Output definitions
send("s")               # request update on all state lists
