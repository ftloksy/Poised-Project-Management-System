package Listener;
import javax.swing.*;

import Editor.FinalisedEditor;
import Editor.PersonEditor;
import Editor.ProjectEditor;
import Frame.PmsFrame;

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
    JTable personTable;
    JTable projectTable;

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
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable;
        this.projectTable = this.mainFrame.pmsTab.projectTab.dbTable;

        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            /** Clear PersonEditor's JTextFields . */
            case 0:
                this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
                this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
                this.personDbEditor.resetField();
                this.mainFrame.msgArea.setText("");
                this.personTable.getSelectionModel().clearSelection();
                break;
            /** Clear ProjectEditor's JTextFields . */
            case 1:
                this.projectDbEditor = this.mainFrame.pmsTab.projectTab.projectEditor ;
                this.projectDbEditor.resetField();
                this.projectDbEditor.updatePersonList();
                this.projectDbEditor.bdgType.setSelectedItem("");
                this.projectTable.getSelectionModel().clearSelection();
                break;
        }
    }
}
