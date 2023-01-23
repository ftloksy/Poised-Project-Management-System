import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;

/**
 * This is Listener for [Delete] Button, When User click the Button, 
 * will trigger this action.
 * The Listener will delete Project table record follow ProjectNumber, Person table record follow id .
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         ProjectEditor 
 * @see         PersonEditor
 */
public class ListenerDelete implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    ProjectTab projectTab ;
    ProjectTable projectTable;
    ProjectEditor projectDbEditor;


    /**
     * ListenerDelete constructor
     * 
     * @param motherFrame   the main Frame ( Root Frame )
     * @param dbPosie       the Database Handler.
     */
    public ListenerDelete(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    /**
     * When User click the [Delete] Button, will trigger the action.
     * If you click the PmsTab, it will change the index,
     * and hava different action. 
     * Listener will handle PersonEditor if the PmsTab's Selected Index is 0.
     * Listener will handle ProjectEditor if the PmsTab's Selected Index is 1.
     */
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
            /** in PersonTab page. */
            case 0:
                String id = this.personDbEditor.idText.getText();
                if ( !id.equals("Id") ) {
                    try {
                        this.dbHandler.deletePerson( id );
                        this.mainFrame.msgArea.setText("Delete is Success.");
                        this.personTable.flashTable();
                    } catch (SQLException pe) { 
                        /** 
                         * If in the Project table has any Customer, Project Manager, ....
                         * is ref to this Person, User cann't delete the Person.
                         */
                        String sqlCode = pe.getSQLState();
                        if ( sqlCode.equals("23000") ) {
                            this.mainFrame.msgArea.setText(
                                 "This person is assign in a Posied Project."
                                + " You cannot simple to delete it." );
                        } else {
                            this.mainFrame.msgArea.setText(pe.getMessage());
                        }
                    }
                } else {
                    this.mainFrame.msgArea.setText("You haven't choice a Person record.");
                }
                break;
            /** in ProjectTab page. */
            case 1:
                /** 
                 * Delete Project record and follow the ProjectNumber. 
                 * then flash the ProjectEditor.
                 */
                String projectNoVal = this.projectDbEditor.projectNoText.getText();
                
                try {
                    this.dbHandler.deleteProject( projectNoVal );
                    this.projectTable.flashTable();
                    this.projectDbEditor.updatePersonList();
                    this.projectDbEditor.bdgType.setSelectedItem("");
                    this.mainFrame.msgArea.setText("Delete Project Record Complete.");
                } catch ( SQLException pje)  {
                    this.mainFrame.msgArea.setText(pje.getMessage());
                }
                break;
        }
    }
}
