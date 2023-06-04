package Listener;
import java.awt.event.*;
import javax.swing.* ;

import Handler.MysqlHandler;
import Editor.PersonEditor;
import Editor.ProjectEditor;
import Frame.PmsFrame;
import Tab.PersonTab;
import Tab.ProjectTab;
import Table.PersonTable;
import Table.ProjectTable;

import java.sql.* ;

/**
 * This is Listener for [Enter] Button, When User click the Button, 
 * will trigger this action.
 * The Listener will get PersonEditor or ProjectEditor 
 * to Enter Project or Person table record .
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         ProjectEditor 
 * @see         PersonEditor
 * @see         MysqlHandler
 */
public class ListenerEnter implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonTab personTab ;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTab projectTab ;
    ProjectTable projectTable;
    ProjectEditor projectDbEditor;
    
    /**
     * ListenerEnter constructor
     * 
     * @param motherFrame    the main Frame ( Root Frame )
     * @param dbPosie        the Database Handler.
     */
    public ListenerEnter(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    /**
     * When User click the [Enter] Button, will trigger the action.
     * If you click the PmsTab, it will change the index,
     * and hava different action. 
     * Listener will handle PersonEditor if the PmsTab's Selected Index is 0.
     * Listener will use PersonEditor JTextField's value to Insert record to Person data Table.
     * Listener will handle ProjectEditor if the PmsTab's Selected Index is 1.
     * Listener will use ProjectEditor JTextField's value to Insert record to Project data Table.
     */    
    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personTab = this.mainFrame.pmsTab.personTab ;
        this.personDbEditor = this.personTab.dbEditor ;
        this.personTable = this.personTab.dbTable ;

        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.projectDbEditor = this.projectTab.projectEditor ;
        this.projectTable = this.projectTab.dbTable ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            /* get PersonEditor JTextFields's value to insert to Person data table. */
            case 0:
                try {
                    this.dbHandler.insertPersonRecord(
                        this.personDbEditor.firstNameText.getText(),
                        this.personDbEditor.surNameText.getText(),
                        this.personDbEditor.telephoneText.getText(),
                        this.personDbEditor.emalAddressText.getText(),
                        this.personDbEditor.physicalAddressText.getText()
                    );
                    this.mainFrame.msgArea.setText("INSERT is Success.");
                    this.personDbEditor.resetField();
                    this.personTable.flashTable();
                } catch (SQLException pe) {
                    this.mainFrame.msgArea.setText(pe.getMessage());
                }
                break;
            /* get ProjectEditor JTextFields's value to insert to Project data table, */
            case 1:
            
                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                String erfNumberVal = this.projectDbEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                String deadlineVal = this.projectDbEditor.deadlineText.getText() ;

                String completedDate = this.projectDbEditor.completedDateText.getText();
            
                String buildingTypeVal = this.projectDbEditor.bdgType.getSelectedItem().toString() ;
                String isFinalised = this.projectDbEditor.setFinalised.getSelectedItem().toString();
                /**
                 * In Finalised's JComboBox value "Yes" map to 1, 
                 * Other map to 0.
                 */
                if ( isFinalised.equals("Yes")) {
                    isFinalised = "1";
                } else {
                    isFinalised = "0";
                };

                try {
                        /* 
                         * The DefaultComboxBoxModel's row format is id:SurName FirstName 
                         * so use ":" to cut the data, and get first item (id) .
                         */ 
                        String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString().split(":")[0] ;
                        String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString().split(":")[0] ;
                        String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString().split(":")[0] ;
                        String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString().split(":")[0] ;
                        String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString().split(":")[0] ;
                        
                        try {
                            this.dbHandler.insertProjectRecord(
                                projectNameVal,
                                buildingTypeVal,
                                physicalAddressVal,
                                erfNumberVal,
                                feeChargedVal,
                                paidTodateVal,
                                deadlineVal,
                                architectVal,
                                contractorVal,
                                customerVal,
                                managerVal,
                                engineerVal,
                                isFinalised,
                                completedDate
                            );
                            this.projectTable.flashTable();
                            this.projectDbEditor.resetField();
                            this.projectDbEditor.updatePersonList();
                            this.mainFrame.msgArea.setText("INSERT Project Record Complete.");
                        } catch ( SQLException pje)  {
                            this.mainFrame.msgArea.setText(pje.getMessage());
                        }
                        
                } catch ( NullPointerException Ne ) {
                    this.mainFrame.msgArea.setText("Please complete input field");
                }
                break;
        }
    }
}
