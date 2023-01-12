import javax.swing.*;
import java.awt.event.*;

public class ListenerListAll implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;

    ProjectEditor projectDbEditor;
    ProjectTable projectTable;
    
    ListenerListAll(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;

        this.projectDbEditor = this.mainFrame.pmsTab.projectTab.dbEditor ;
        this.projectTable = this.mainFrame.pmsTab.projectTab.dbTable ;
        
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:

                this.mainFrame.msgArea.setText("List all Person's Record.");
                this.personDbEditor.resetField();
                this.personTable.flashTable();

                break;
            case 1:

                this.mainFrame.msgArea.setText("List all Project's Record.");
                this.projectDbEditor.resetField();
                this.projectDbEditor.updatePersonList();
                this.projectTable.flashTable();
                break;
        }
    }
}
