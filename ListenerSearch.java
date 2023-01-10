import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;
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
            
                String projectNoVal = this.projectDbEditor.projectNoText.getText();
                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                String erfNumberVal = this.projectDbEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                String deadlineVal = this.projectDbEditor.deadlineText.getText() ;
                
		String sqlTail = "";
                if ( !this.projectTab.bdgType.isSelectionEmpty() ) {
                     String buildingTypeVal = this.projectTab.bdgType.getSelectedValue().toString() ;
		     sqlTail += "AND BuildingType = '" + buildingTypeVal + "' " 
                };

                if ( !this.projectTab.setArchitect.isSelectionEmpty() ) {
                     String architectVal = this.projectTab.setArchitect.getSelectedValue().toString() ;
		     sqlTail += "AND Architect = '" + architectVal + "' " 
                };

                if ( !this.projectTab.setContractor.isSelectionEmpty() ) {
                     String contractorVal = this.projectTab.setContractor.getSelectedValue().toString() ;
		     sqlTail += "AND Contractor = '" + contractorVal + "' " 
                };

                if ( !this.projectTab.setCustomer.isSelectionEmpty() ) {
                     String customerVal = this.projectTab.setCustomer.getSelectedValue().toString() ;
		     sqlTail += "AND Customer = '" + customerVal + "' " 
                };

                if ( !this.projectTab.setManager.isSelectionEmpty() ) {
                     String managerVal = this.projectTab.setManager.getSelectedValue().toString() ; 
		     sqlTail += "AND ProjectManager = '" + managerVal + "' " 
                };

                if ( !this.projectTab.setEngineer.isSelectionEmpty() ) {
                     String engineerVal = this.projectTab.setEngineer.getSelectedValue().toString() ;
		     sqlTail += "AND StructuralEngineer = '" + engineerVal + "' " 
                };

                
                try {
                    this.dbHandler.searchProjectRecord(
                        projectNoVal,
                        projectNameVal,
                        physicalAddressVal,
                        erfNumberVal,
                        feeChargedVal,
                        paidTodateVal,
                        deadlineVal,
                        sqlTail
                    );
                    this.projectTable.flashTable();
                    this.projectTab.updatePersonList();
                    this.projectTab.bdgType.clearSelection();
                    this.mainFrame.msgArea.setText("UPDATE Project Record Complete.");
                    
                } catch ( SQLException pje)  {
                    this.mainFrame.msgArea.setText(pje.getMessage());
                }
                        
                break;
        }
    }
}
