package Listener;
import javax.swing.*;

import Handler.MysqlHandler;
import Table.PersonTable;
import Table.ProjectTable;
import Frame.PmsFrame;
import Editor.PersonEditor;
import Editor.ProjectEditor;
import Editor.FinalisedEditor;

import java.awt.event.*;
import java.sql.SQLException;

/**
 * This is Listener for ListAll Button, When User click the Button, 
 * will trigger this action.
 * It will update the ProjectTable JTable or PersonTable JTable
 * and List all record in JTables.
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         PersonTable 
 * @see         ProjectTable
 * @see         MysqlHandler
 */
public class ListenerListAll implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;

    ProjectEditor projectDbEditor;
    ProjectTable projectTable;

    FinalisedEditor finalisedDbEditor;
    
    /**
     * ListenerListAll constructor
     * 
     * It will update the ProjectTable JTable or PersonTable JTable
     * and List all record in JTables.
     * 
     * @param motherFrame  the main Frame ( Root Frame )
     * @param dbPosie      the DatabaseHandler.
     */
    public ListenerListAll(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    /**
     * Flash PersonTable JTable in the pmsTab's selected index is 0,
     * And Display all Person database records in the PersonTable.<br/>
     * Flash ProjectTable JTable in the pmsTab's selected index is 1.
     * And Display all Project database records in the ProjectTable.<br/>
     */
    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;

        this.projectDbEditor = this.mainFrame.pmsTab.projectTab.projectEditor ;
        this.projectTable = this.mainFrame.pmsTab.projectTab.dbTable ;

        this.finalisedDbEditor = this.mainFrame.pmsTab.projectTab.finalisedEditor;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                this.mainFrame.msgArea.setText("List all Person's Record.");
                this.personDbEditor.resetField();
                try {
                    this.personTable.flashTable();
                } catch (SQLException ft) {
                    this.mainFrame.msgArea.setText( ft.getMessage() );
                }
                break;
            case 1:
                this.mainFrame.msgArea.setText("List all Project's Record.");
                this.finalisedDbEditor.resetField();
                this.projectDbEditor.updatePersonList();
                try {
                    this.projectTable.flashTable();
                } catch (SQLException ft) {
                    this.mainFrame.msgArea.setText( ft.getMessage() );
                }
                break;
        }
    }
}
