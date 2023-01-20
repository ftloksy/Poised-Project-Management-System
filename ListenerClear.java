import javax.swing.*;
import java.awt.event.*;

/*
 * This is Listener for [Clear] Button, When User click the Button, 
 * will trigger this action.
 * The Listener will clear ProjectEditor and PersonEditor all JTextField .
 */
public class ListenerClear implements ActionListener {
    PmsFrame mainFrame;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    ProjectEditor projectDbEditor;
    
    ListenerClear(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;

        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            /* Clear PersonEditor's JTextFields . */
            case 0:
                this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
                this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
                this.personDbEditor.resetField();
                this.mainFrame.msgArea.setText("");
                break;
            /* Clear ProjectEditor's JTextFields . */
            case 1:
                this.projectDbEditor = this.mainFrame.pmsTab.projectTab.projectEditor ;
                this.projectDbEditor.resetField();
                this.projectDbEditor.updatePersonList();
                this.projectDbEditor.bdgType.setSelectedItem("");
                break;
        }
    }
}
