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

                Vector<Vector<String>> searchResult = this.dbHandler.searchPersonRecord(
                    this.personDbEditor.firstNameText.getText(),
                    this.personDbEditor.surNameText.getText(),
                    this.personDbEditor.telephoneText.getText(),
                    this.personDbEditor.emalAddressText.getText(),
                    this.personDbEditor.physicalAddressText.getText()
                );
                this.mainFrame.msgArea.setText("Search is Complete.");
                this.personDbEditor.resetField();
                this.personTable.reNewTable( searchResult );

                break;
            case 1:
            
		        String sqlTail = "SELECT "
                + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"
                + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer"
                + " FROM ProjectView WHERE ProjectName like '%" ;



                // String projectNoVal = this.projectDbEditor.projectNoText.getText();
                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();

                sqlTail += projectNameVal + "%' AND PhysicalAddress LIKE '%" + physicalAddressVal + "%'";

                // String deadlineVal = this.projectDbEditor.deadlineText.getText() ;
                

                // String erfNumberVal = this.projectDbEditor.erfNoText.getText() ;
                // String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                // String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;

                String erfNoVal = this.projectDbEditor.erfNoText.getText() ;
		        sqlTail += " AND ERFNumber LIKE '%" + erfNoVal + "%'" ;

            
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
		        sqlTail += " AND FeeCharged LIKE '%" + feeChargedVal + "%' " ;
 
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
		        sqlTail += "AND PaidToDate LIKE '%" + paidTodateVal + "%' " ;

                String buildingTypeVal = this.projectDbEditor.bdgType.getSelectedItem().toString() ;
		        sqlTail += "AND BuildingType LIKE '%" + buildingTypeVal + "%' " ;

                String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString() ;
		        sqlTail += "AND Architect LIKE '%" + architectVal + "%' " ;

                String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString() ;
		        sqlTail += "AND Contractor LIKE '%" + contractorVal + "%' " ;

                String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString() ;
		        sqlTail += "AND Customer LIKE '%" + customerVal + "%' " ;

                String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString() ; 
		        sqlTail += "AND ProjectManager LIKE '%" + managerVal + "%' " ; 

                String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString() ;
		        sqlTail += "AND StructuralEngineer LIKE '%" + engineerVal + "%' " ; 

                this.mainFrame.msgArea.setText("This sql Tail : " + sqlTail);
                System.out.println(sqlTail);
                
                // try {
                //     this.dbHandler.searchProjectRecord(
                //         projectNoVal,
                //         projectNameVal,
                //         physicalAddressVal,
                //         erfNumberVal,
                //         feeChargedVal,
                //         paidTodateVal,
                //         deadlineVal,
                //         sqlTail
                //     );
                //     this.projectTable.flashTable();
                //     this.projectTab.updatePersonList();
                //     this.projectTab.bdgType.clearSelection();
                //     this.mainFrame.msgArea.setText("UPDATE Project Record Complete.");
                    
                // } catch ( SQLException pje)  {
                //     this.mainFrame.msgArea.setText(pje.getMessage());
                // }
                        
                break;
        }
    }
}
