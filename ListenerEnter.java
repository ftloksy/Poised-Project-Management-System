import java.awt.event.*;
import javax.swing.* ;
import java.sql.* ;

public class ListenerEnter implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonTab personTab ;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTab projectTab ;
    ProjectTable projectTable;
    ProjectEditor projectEditor;
    
    ListenerEnter(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personTab = this.mainFrame.pmsTab.personTab ;
        this.personDbEditor = this.personTab.dbEditor ;
        this.personTable = this.personTab.dbTable ;

        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.projectEditor = this.projectTab.dbEditor ;
        this.projectTable = this.projectTab.dbTable ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
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
            case 1:
            
                String projectNameVal = this.projectEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectEditor.physicalAddressText.getText();
                String erfNumberVal = this.projectEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectEditor.paidTodateText.getText() ;
                String deadlineVal = this.projectEditor.deadlineText.getText() ;
                
                try {
                        
                        String buildingTypeVal = this.projectEditor.bdgType.getSelectedItem().toString() ;
                        
                        String architectVal = this.projectEditor.setArchitect.getSelectedItem().toString().split(":")[0] ;
                        String contractorVal = this.projectEditor.setContractor.getSelectedItem().toString().split(":")[0] ;
                        String customerVal = this.projectEditor.setCustomer.getSelectedItem().toString().split(":")[0] ;
                        String managerVal = this.projectEditor.setManager.getSelectedItem().toString().split(":")[0] ;
                        String engineerVal = this.projectEditor.setEngineer.getSelectedItem().toString().split(":")[0] ;
                        
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
                                engineerVal
                            );
                            this.projectTable.flashTable();
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
