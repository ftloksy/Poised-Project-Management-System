import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;
import java.util.Vector;

public class ListenerListAll implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    
    ListenerListAll(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
        
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:

                this.mainFrame.msgArea.setText("List all Person's Record.");
                this.personDbEditor.resetField();
                this.personTable.flashTable();

                break;
            case 1:
                break;
        }
    }
}
