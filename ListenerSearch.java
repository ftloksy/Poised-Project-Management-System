import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ListenerSearch implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTable projectTable;
    ProjectEditor projectDbEditor;
    ProjectTab projectTab ;
    
    ListenerSearch (PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.projectDbEditor = this.projectTab.dbEditor ;
        this.projectTable = this.projectTab.dbTable ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:

                Vector<Vector<String>> searchPersonResult = this.dbHandler.searchPersonRecord(
                    this.personDbEditor.firstNameText.getText(),
                    this.personDbEditor.surNameText.getText(),
                    this.personDbEditor.telephoneText.getText(),
                    this.personDbEditor.emalAddressText.getText(),
                    this.personDbEditor.physicalAddressText.getText()
                );
                this.mainFrame.msgArea.setText("Search is Complete.");
                this.personDbEditor.resetField();
                this.personTable.reNewTable( searchPersonResult );

                break;
            case 1:

                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                String erfNoVal = this.projectDbEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                String buildingTypeVal = this.projectDbEditor.bdgType.getSelectedItem().toString() ;
                String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString() ;
                String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString() ;
                String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString() ;
                String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString() ; 
                String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString() ;

                Vector<Vector<String>> searchProjectResult = this.dbHandler.searchProjectRecord(
                    projectNameVal,
                    physicalAddressVal, 
                    erfNoVal,
                    feeChargedVal,
                    paidTodateVal,
                    buildingTypeVal,
                    architectVal,
                    contractorVal,
                    customerVal,
                    managerVal,
                    engineerVal 
                );

                this.mainFrame.msgArea.setText("Search is Complete.");
                this.projectDbEditor.resetField();
                this.projectTable.reNewTable( searchProjectResult );

                break;
        }
    }
}
