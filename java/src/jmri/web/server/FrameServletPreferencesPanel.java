/*============================================================================*
 * WARNING      This class contains automatically modified code.      WARNING *
 *                                                                            *
 * The method initComponents() and the variable declarations between the      *
 * "// Variables declaration - do not modify" and                             *
 * "// End of variables declaration" comments will be overwritten if modified *
 * by hand. Using the NetBeans IDE to edit this file is strongly recommended. *
 *                                                                            *
 * See http://jmri.org/help/en/html/doc/Technical/NetBeansGUIEditor.shtml for *
 * more information.                                                          *
 *============================================================================*/
package jmri.web.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import jmri.InstanceManager;
import jmri.swing.DefaultEditableListModel;
import jmri.swing.DefaultListCellEditor;
import jmri.swing.EditableList;
import jmri.swing.PreferencesPanel;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

/**
 *
 * @author Randall Wood
 */
public class FrameServletPreferencesPanel extends JPanel implements ListDataListener, PreferencesPanel {

    /**
     * Creates new form FrameServletPreferencesPanel
     */
    public FrameServletPreferencesPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new BindingGroup();

        framesEnabled = new ButtonGroup();
        preferences = InstanceManager.getDefault(WebServerPreferences.class);
        enableFramesPnl = new JPanel();
        enableFrames = new JRadioButton();
        clickDelay = new JSpinner();
        refreshDelay = new JSpinner();
        useAjax = new JCheckBox();
        refreshDelayLbl = new JLabel();
        clickDelayLbl = new JLabel();
        disallowedFramesLbl = new JLabel();
        jScrollPane1 = new JScrollPane();
        disallowedFrames = new EditableList<String>();
        disableFramesPnl = new JPanel();
        disableFrames = new JRadioButton();
        redirectToPanels = new JCheckBox();

        framesEnabled.add(enableFrames);
        framesEnabled.add(disableFrames);

        preferences.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                preferencesPropertyChange(evt);
            }
        });

        enableFrames.setText(Bundle.getMessage("LabelEnableFrames")); // NOI18N

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, preferences, ELProperty.create("${!disableFrames}"), enableFrames, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        clickDelay.setModel(new SpinnerNumberModel(1, 0, 999, 1));
        clickDelay.setToolTipText(Bundle.getMessage("ToolTipClickDelay")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, preferences, ELProperty.create("${clickDelay}"), clickDelay, BeanProperty.create("value"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), clickDelay, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        refreshDelay.setModel(new SpinnerNumberModel(5, 0, 999, 1));
        refreshDelay.setToolTipText(Bundle.getMessage("ToolTipRefreshDelay")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, preferences, ELProperty.create("${refreshDelay}"), refreshDelay, BeanProperty.create("value"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), refreshDelay, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        useAjax.setText(Bundle.getMessage("LabelUseAjax")); // NOI18N
        useAjax.setToolTipText(Bundle.getMessage("ToolTipUseAjax")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, preferences, ELProperty.create("${useAjax}"), useAjax, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), useAjax, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        refreshDelayLbl.setText(Bundle.getMessage("LabelRefreshDelay")); // NOI18N
        refreshDelayLbl.setToolTipText(Bundle.getMessage("ToolTipRefreshDelay")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), refreshDelayLbl, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        clickDelayLbl.setText(Bundle.getMessage("LabelClickDelay")); // NOI18N
        clickDelayLbl.setToolTipText(Bundle.getMessage("ToolTipClickDelay")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), clickDelayLbl, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        disallowedFramesLbl.setText(Bundle.getMessage("LabelDisallowedFrames")); // NOI18N
        disallowedFramesLbl.setToolTipText(Bundle.getMessage("ToolTipDisallowedFrames")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), disallowedFramesLbl, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        disallowedFrames.setModel(new DefaultEditableListModel<String>());
        disallowedFrames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        disallowedFrames.setToolTipText(Bundle.getMessage("ToolTipDisallowedFrames")); // NOI18N
        disallowedFrames.setListCellEditor(new DefaultListCellEditor<>(new JTextField()));

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, enableFrames, ELProperty.create("${selected}"), disallowedFrames, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        DefaultEditableListModel<String> model = (DefaultEditableListModel<String>) this.disallowedFrames.getModel();
        model.addListDataListener(this);
        for (String frame : this.preferences.getDisallowedFrames()) {
            model.addElement(frame);
        }
        model.addElement(" ");
        jScrollPane1.setViewportView(disallowedFrames);

        GroupLayout enableFramesPnlLayout = new GroupLayout(enableFramesPnl);
        enableFramesPnl.setLayout(enableFramesPnlLayout);
        enableFramesPnlLayout.setHorizontalGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(enableFramesPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(enableFramesPnlLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(enableFramesPnlLayout.createSequentialGroup()
                                .addComponent(clickDelay, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clickDelayLbl, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
                            .addGroup(enableFramesPnlLayout.createSequentialGroup()
                                .addComponent(refreshDelay, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshDelayLbl, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
                            .addComponent(useAjax, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(disallowedFramesLbl, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                    .addComponent(enableFrames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        enableFramesPnlLayout.setVerticalGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(enableFramesPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enableFrames)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(clickDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(clickDelayLbl)
                    .addComponent(disallowedFramesLbl))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(enableFramesPnlLayout.createSequentialGroup()
                        .addGroup(enableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshDelayLbl))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(useAjax)
                        .addContainerGap(108, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );

        disableFrames.setText(Bundle.getMessage("LabelDisableFrames")); // NOI18N
        disableFrames.setToolTipText(Bundle.getMessage("ToolTipDisableFrames")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, preferences, ELProperty.create("${disableFrames}"), disableFrames, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        redirectToPanels.setText(Bundle.getMessage("LabelRedirectFramesToPanels")); // NOI18N
        redirectToPanels.setToolTipText(Bundle.getMessage("ToolTipRedirectFramesToPanels")); // NOI18N

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, preferences, ELProperty.create("${redirectFramesToPanels}"), redirectToPanels, BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, disableFrames, ELProperty.create("${selected}"), redirectToPanels, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        GroupLayout disableFramesPnlLayout = new GroupLayout(disableFramesPnl);
        disableFramesPnl.setLayout(disableFramesPnlLayout);
        disableFramesPnlLayout.setHorizontalGroup(disableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(disableFramesPnlLayout.createSequentialGroup()
                .addGroup(disableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(disableFramesPnlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(disableFrames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(disableFramesPnlLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(redirectToPanels, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        disableFramesPnlLayout.setVerticalGroup(disableFramesPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(disableFramesPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(disableFrames, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redirectToPanels)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(enableFramesPnl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(disableFramesPnl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(enableFramesPnl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disableFramesPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void preferencesPropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_preferencesPropertyChange
        if (evt.getPropertyName().equals(WebServerPreferences.DISALLOWED_FRAMES)) {
            DefaultEditableListModel<String> model = (DefaultEditableListModel<String>) disallowedFrames.getModel();
            model.removeListDataListener(this);
            model.removeAllElements();
            for (String frame : preferences.getDisallowedFrames()) {
                model.addElement(frame);
            }
            model.addElement(" ");
            model.addListDataListener(this);
        }
    }//GEN-LAST:event_preferencesPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JSpinner clickDelay;
    private JLabel clickDelayLbl;
    private JRadioButton disableFrames;
    private JPanel disableFramesPnl;
    private EditableList<String> disallowedFrames;
    private JLabel disallowedFramesLbl;
    private JRadioButton enableFrames;
    private JPanel enableFramesPnl;
    private ButtonGroup framesEnabled;
    private JScrollPane jScrollPane1;
    private WebServerPreferences preferences;
    private JCheckBox redirectToPanels;
    private JSpinner refreshDelay;
    private JLabel refreshDelayLbl;
    private JCheckBox useAjax;
    private BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getPreferencesItem() {
        return Bundle.getMessage("PreferencesItem");
    }

    @Override
    public String getPreferencesItemText() {
        return Bundle.getMessage("TitleDelayPanel");
    }

    @Override
    public String getTabbedPreferencesTitle() {
        return Bundle.getMessage("TitleDelayPanel");
    }

    @Override
    public String getLabelKey() {
        return null;
    }

    @Override
    public JComponent getPreferencesComponent() {
        return this;
    }

    @Override
    public boolean isPersistant() {
        return false;
    }

    @Override
    public String getPreferencesTooltip() {
        return null;
    }

    @Override
    public void savePreferences() {
        if (this.isDirty()) {
            this.preferences.save();
        }
    }

    @Override
    public boolean isDirty() {
        return this.preferences.isDirty();
    }

    @Override
    public boolean isRestartRequired() {
        return this.preferences.isRestartRequired();
    }

    @Override
    public boolean isPreferencesValid() {
        return true; // no validity checking performed

    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        // do nothing
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        // do nothing
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        DefaultEditableListModel<String> model = (DefaultEditableListModel<String>) disallowedFrames.getModel();
        if (!model.getElementAt(model.getSize() - 1).equals(" ")) {
            model.addElement(" ");
        } else if (model.getElementAt(e.getIndex0()).isEmpty()) {
            model.removeElementAt(e.getIndex0());
        }
        ArrayList<String> frames = new ArrayList<>();
        Enumeration<String> elements = model.elements();
        while (elements.hasMoreElements()) {
            String frame = elements.nextElement().trim();
            if (!frame.isEmpty()) {
                frames.add(frame);
            }
        }
        this.preferences.setDisallowedFrames(frames.toArray(new String[frames.size()]));
    }
}
