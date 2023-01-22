import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

/**
 * This is Listener for Search Button, When User click the Button, 
 * will trigger this action.
 */
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
    SearchByProjectNameEditor searchByProjectNameEditor;
    
    /**
     * ListenerSearch constructor
     * 
     * @param motherFrame the main Frame ( Root Frame )
     * @param dbPosie the DatabaseHandler.
     */
    public ListenerSearch (PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    /**
     * Get data from PersonEditor and Search in Person data table then
     * Update the PersonTable JTable when the pmsTab selected index is 0 .<br/>
     * 
     * Get data from ProjectEditor and Search in Project data table then
     * Update the ProjectTable JTable when the pmsTab selected index is 1 and projectTab selected index is 0.<br/>
     * 
     * Get data from SearchByProjectNumberEditor and Search in Project data table follow Project Number then
     * Update ProjectTable JTable when the pmsTab selected index is 1 and projectTab selected index is 3.<br/>
     * 
     * Get data from SearchByProjectNameEditor and Search in Project data table follow Project Name then
     * Update ProjectTable JTable when the pmsTab selected index is 1 and projectTab selected index is 4.<br/>
     */
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
        this.searchByProjectNameEditor = this.projectTab.searchByProjectNameEditor ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        int projectTabIndex = this.projectTabPane.getSelectedIndex();

        switch ( index ) {
            /* 
             * Get data from PersonEditor and Search in Person data table then
             * Update the PersonTable JTable.
             */
            case 0:
                try {
                    Vector<Vector<String>> searchPersonResult = this.dbHandler.searchPersonRecord(
                        this.personDbEditor.firstNameText.getText(),
                        this.personDbEditor.surNameText.getText(),
                        this.personDbEditor.telephoneText.getText(),
                        this.personDbEditor.emalAddressText.getText(),
                        this.personDbEditor.physicalAddressText.getText()
                    );
                    this.mainFrame.msgArea.setText("Search is Complete.");
                    //this.personDbEditor.resetField();
                    this.personTable.reNewTable( searchPersonResult );
                } catch (SQLException spr) {
                    this.mainFrame.msgArea.setText( spr.getMessage() );
                }

                break;
            
            /* 
             * Get data from PorjectEditor and Search in Project data table then
             * Update the ProjectTable JTable.
             */
            case 1:

                String projectNameVal = this.projectDbEditor.projectNameText.getText() ;
                String physicalAddressVal = this.projectDbEditor.physicalAddressText.getText();
                String erfNoVal = this.projectDbEditor.erfNoText.getText() ;
                String feeChargedVal = this.projectDbEditor.feeChargedText.getText() ;
                String paidTodateVal = this.projectDbEditor.paidTodateText.getText() ;
                String deadLine = this.projectDbEditor.deadlineText.getText() ;
                String buildingTypeVal = this.projectDbEditor.bdgType.getSelectedItem().toString() ;
                String architectVal = this.projectDbEditor.setArchitect.getSelectedItem().toString() ;
                String contractorVal = this.projectDbEditor.setContractor.getSelectedItem().toString() ;
                String customerVal = this.projectDbEditor.setCustomer.getSelectedItem().toString() ;
                String managerVal = this.projectDbEditor.setManager.getSelectedItem().toString() ; 
                String engineerVal = this.projectDbEditor.setEngineer.getSelectedItem().toString() ;

                String searchByProjectNumberVal = this.searchByProjectNumberEditor.projectNoText.getText();
                String searchByProjectNameVal = this.searchByProjectNameEditor.projectNameText.getText();

                /* 
                 * Finalised DefaultComboBoxModel has row ['', 'Yes', 'No'] but the Project table's
                 * Boolean date type. 0, 1 , so need to map 'Yes' to 1
                 * and other to 0.
                 */
                String isFinalised = this.projectDbEditor.setFinalised.getSelectedItem().toString();
                if ( isFinalised.equals("Yes")) {
                    isFinalised = "1";
                } else {
                    isFinalised = "0";
                };

                switch ( projectTabIndex ) {
                     /* 
                      * In ProjectTab, these pages
                      * Record Handler, Search by Project Number,
                      * Search by Project Name. 4 pages has searh button.
                      *
                      * Record Handler page.
                      */
                    case 0:
                        try {
                            Vector<Vector<String>> searchProjectResult = this.dbHandler.searchProjectRecord(
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
                                isFinalised
                            );
		
		                    this.mainFrame.msgArea.setText("Search Complete.");
		                    //this.projectDbEditor.resetField();
		                    this.projectTable.reNewTable( searchProjectResult );

                        } catch ( SQLException spj ) {
                            this.mainFrame.msgArea.setText( spj.getMessage() );
                        }
                        break;

                    /* Search Project Number page */
                    case 3:
                        try {
                            Vector<Vector<String>> searchByProjectNumberResult = this.dbHandler.selectByProjectNumberRecord(
                                searchByProjectNumberVal );
                            this.mainFrame.msgArea.setText("Search Complete"); 
                            //this.projectDbEditor.resetField();
                            this.projectTable.reNewTable( searchByProjectNumberResult );
                        } catch ( SQLException spjno ) {
                            this.mainFrame.msgArea.setText( spjno.getMessage() );
                        }
                        break;
                    
                    /* Search by Project Name page */
                    case 4:
                        try {
                            Vector<Vector<String>> searchByProjectNameResult = this.dbHandler.selectByProjectNameRecord(
                                searchByProjectNameVal );
                            this.mainFrame.msgArea.setText("Search Complete"); 
                            //this.projectDbEditor.resetField();
                            this.projectTable.reNewTable( searchByProjectNameResult );
                        } catch ( SQLException spjne ) {
                            this.mainFrame.msgArea.setText( spjne.getMessage() );
                        }
                        break;
                }

                break;
        }
    }
}
