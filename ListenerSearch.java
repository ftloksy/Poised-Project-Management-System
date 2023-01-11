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
            
                // String projectNoVal = this.projectDbEditor.projectNoText.getText();
                // String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                // String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                // String erfNumberVal = this.projectDbEditor.erfNoText.getText() ;
                // String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                // String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                // String deadlineVal = this.projectDbEditor.deadlineText.getText() ;
                
		String sqlTail = "";
            //     if ( !this.projectTab.bdgType.isSelectionEmpty() ) {
            //          String buildingTypeVal = this.projectTab.bdgType.getSelectedValue().toString() ;
		    //  sqlTail += "AND BuildingType = '" + buildingTypeVal + "' " ;
            //     };

                if ( this.projectDbEditor.setArchitect.getSelectedItem()  != "" ) {
                     String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString() ;
		     sqlTail += "AND Architect = '" + architectVal + "' " ;
                };

                if ( this.projectDbEditor.setContractor.getSelectedItem() != "" ) {
                     String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString() ;
		     sqlTail += "AND Contractor = '" + contractorVal + "' " ;
                };

                if ( this.projectDbEditor.setCustomer.getSelectedItem() != "") {
                     String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString() ;
		     sqlTail += "AND Customer = '" + customerVal + "' " ;
                };

                if ( this.projectDbEditor.setManager.getSelectedItem() != "" ) {
                     String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString() ; 
		     sqlTail += "AND ProjectManager = '" + managerVal + "' " ; 
                };

                if ( this.projectDbEditor.setEngineer.getSelectedItem() != "" ) {
                     String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString() ;
		     sqlTail += "AND StructuralEngineer = '" + engineerVal + "' " ; 
                };

                this.mainFrame.msgArea.setText("This sql Tail : " + sqlTail);
                
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
