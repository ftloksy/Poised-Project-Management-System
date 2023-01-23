import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;

/**
 * This is Listener for Update Button, When User click the Button, 
 * will trigger this action.
 */
public class ListenerUpdate implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTable projectTable;
    ProjectEditor projectDbEditor;
    ProjectTab projectTab;


    /**
     * ListenerUpdate constructor
     * 
     * @param motherFrame the main Frame ( Root Frame )
     * @param dbPosie the DatabaseHandler.
     */
    public ListenerUpdate(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    /** 
     * Get the JTextField's information PersonEditor or ProjectEditor 
     * Update database Person table or Project table.
     * If the pmsTab selected index is 0, it will update the Person data table.<br/>
     * If the pmsTab selected index is 1, it will update the Project data table.<br/>
     */
    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.projectDbEditor = this.projectTab.projectEditor ;
        this.projectTable = this.projectTab.dbTable ;
        
        /* On ProjectTab page or PersonTab page the action isn't same  */
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                /* Get data from PersonEditor JTextField and update to Person table in database.  */
                try {
                    this.dbHandler.updatePersonRecord(
                        this.personDbEditor.idText.getText(),
                        this.personDbEditor.firstNameText.getText(),
                        this.personDbEditor.surNameText.getText(),
                        this.personDbEditor.telephoneText.getText(),
                        this.personDbEditor.emalAddressText.getText(),
                        this.personDbEditor.physicalAddressText.getText()
                    );
                    this.mainFrame.msgArea.setText("Update is Success.");
                    this.personDbEditor.resetField();
                    this.personTable.flashTable();
                } catch (SQLException pe) {
                    String sqlCode = pe.getSQLState();

                    /* 
                     * Data isn't complete from PersonEditor JTextField 
                     * and Cann't update to Person table in database. 
                     * Show the error message to msgArea in Main Frame.
                     */
                    if ( sqlCode.equals("22001") ) {
                        this.mainFrame.msgArea.setText(
                             "If you want to delete a record. Please select a record in the table." );
                    } else {
                        this.mainFrame.msgArea.setText(pe.getMessage());
                    }
                }
                break;
            case 1:
            
                /* Get data from ProjectEditor JTextField and update to Project table in database.  */

                String projectNoVal = this.projectDbEditor.projectNoText.getText();
                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                String erfNumberVal = this.projectDbEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                String deadlineVal = this.projectDbEditor.deadlineText.getText() ;
                String completedDate = this.projectDbEditor.completedDateText.getText() ;
                
                try {
                        String buildingTypeVal = this.projectDbEditor.bdgType.getSelectedItem().toString() ;
                        String isFinalised = this.projectDbEditor.setFinalised.getSelectedItem().toString();
                        if ( isFinalised.equals("Yes")) {
                            isFinalised = "1";
                        } else {
                            isFinalised = "0";
                        };
                        
                        /* 
                         * The DefaultComboxBoxModel's row format is id:SurName FirstName 
                         * so use ":" to cut the data, and get first item (id) .
                         */
                        String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString().split(":")[0] ;
                        String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString().split(":")[0] ;
                        String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString().split(":")[0] ;
                        String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString().split(":")[0] ;
                        String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString().split(":")[0] ;
                        
                        /* Update the Project Record. */
                        try {
                            this.dbHandler.updateProjectRecord(
                                projectNoVal,
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
                                completedDate,
                                isFinalised
                            );
                            this.projectTable.flashTable();
                            this.projectDbEditor.updatePersonList();
                            this.projectDbEditor.bdgType.setSelectedItem("");
                            this.mainFrame.msgArea.setText("UPDATE Project Record Complete.");
                            
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
