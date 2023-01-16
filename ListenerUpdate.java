import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;

public class ListenerUpdate implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTable projectTable;
    ProjectEditor projectDbEditor;
    ProjectTab projectTab;
    
    ListenerUpdate(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.projectDbEditor = this.projectTab.projectEditor ;
        this.projectTable = this.projectTab.dbTable ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                try {
                    this.dbHandler.updatePersonRecord(
                        this.personDbEditor.idText.getText(),
                        this.personDbEditor.firstNameText.getText(),
                        this.personDbEditor.surNameText.getText(),
                        this.personDbEditor.telephoneText.getText(),
                        this.personDbEditor.emalAddressText.getText(),
                        this.personDbEditor.physicalAddressText.getText()
                    );
;
                    this.mainFrame.msgArea.setText("Update is Success.");
                    this.personDbEditor.resetField();
                    this.personTable.flashTable();
                } catch (SQLException pe) {
                    String sqlCode = pe.getSQLState();
                    if ( sqlCode.equals("22001") ) {
                        this.mainFrame.msgArea.setText(
                             "If you want to delete a record. Please select a record in the table." );
                    } else {
                        this.mainFrame.msgArea.setText(pe.getMessage());
                    }
                }
                break;
            case 1:
            
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
                        
                        String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString().split(":")[0] ;
                        String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString().split(":")[0] ;
                        String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString().split(":")[0] ;
                        String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString().split(":")[0] ;
                        String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString().split(":")[0] ;
                        
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
