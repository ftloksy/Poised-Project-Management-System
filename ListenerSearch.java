import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ListenerSearch implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    JTabbedPane projectTabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTable projectTable;
    ProjectEditor projectDbEditor;
    ProjectTab projectTab ;
    SearchByProjectNumberEditor searchByProjectNumberEditor;
    
    ListenerSearch (PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.projectTabPane = this.mainFrame.pmsTab.projectTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.projectDbEditor = this.projectTab.projectEditor ;
        this.projectTable = this.projectTab.dbTable ;
        this.searchByProjectNumberEditor = this.projectTab.searchByProjectNumberEditor ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        int projectTabIndex = this.projectTabPane.getSelectedIndex();
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

                //String projectNoVal = this.projectDbEditor.projectNoText.getText() ;
                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                String erfNoVal = this.projectDbEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                String deadLine = this.projectDbEditor.deadlineText.getText() ;
                //String completedDate = this.projectDbEditor.completedDateText.getText();
                String buildingTypeVal = this.projectDbEditor.bdgType.getSelectedItem().toString() ;
                String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString() ;
                String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString() ;
                String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString() ;
                String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString() ; 
                String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString() ;

                String searchByProjectNumberVal = this.searchByProjectNumberEditor.projectNoText.getText();

                String isFinalised = this.projectDbEditor.setFinalised.getSelectedItem().toString();
                if ( isFinalised.equals("Yes")) {
                    isFinalised = "1";
                } else {
                    isFinalised = "0";
                };

                switch ( projectTabIndex ) {
                    case 0:
		                Vector<Vector<String>> searchProjectResult = this.dbHandler.searchProjectRecord(
		                    // projectNoVal,
		                    projectNameVal,
		                    physicalAddressVal, 
		                    erfNoVal,
		                    feeChargedVal,
		                    paidTodateVal,
		                    buildingTypeVal,
		                    deadLine,
		                    architectVal,
		                    contractorVal,
		                    customerVal,
		                    managerVal,
		                    engineerVal,
		                //    completedDate,
		                    isFinalised
		                );
		
		                this.mainFrame.msgArea.setText("Search Complete.");
		                this.projectDbEditor.resetField();
		                this.projectTable.reNewTable( searchProjectResult );
                        break;

                    case 3:
		                Vector<Vector<String>> searchByProjectNumberResult = this.dbHandler.selectByProjectNumberRecord(
		                    searchByProjectNumberVal );
                        this.mainFrame.msgArea.setText("Search Complete"); 
		                this.projectDbEditor.resetField();
		                this.projectTable.reNewTable( searchByProjectNumberResult );
                        //     + searchByProjectNumberVal);
                        break;
                }

                break;
        }
    }
}
