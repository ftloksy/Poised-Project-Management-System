import javax.swing.*;
import java.awt.event.*;

/**
 * This is Listener for [Clear] Button, When User click the Button, 
 * will trigger this action.
 * The Listener will clear ProjectEditor and PersonEditor all JTextField .
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         ProjectEditor 
 * @see         PersonEditor
 * @see         FinalisedEditor
 */
public class ListenerClear implements ActionListener {
    PmsFrame mainFrame;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    ProjectEditor projectDbEditor;

    /**
     * ListenerClear constructor
     * 
     * @param motherFrame the main Frame ( Root Frame )
     */ 
    public ListenerClear(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }

    /** 
     * When user click the button, will target here.
     * In different tab, action is not same.<br/>
     * 
     * In tab 1, will handle the Person.<br/>
     * In tab 2, will handle the Project.<br/>
     */
    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;

        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            /** Clear PersonEditor's JTextFields . */
            case 0:
                this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
                this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
                this.personDbEditor.resetField();
                this.mainFrame.msgArea.setText("");
                break;
            /** Clear ProjectEditor's JTextFields . */
            case 1:
                this.projectDbEditor = this.mainFrame.pmsTab.projectTab.projectEditor ;
                this.projectDbEditor.resetField();
                this.projectDbEditor.updatePersonList();
                this.projectDbEditor.bdgType.setSelectedItem("");
                break;
        }
    }
}
