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
    
    ListenerSearch (PmsFrame motherFrame, MysqlHandler dbPosie){
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
                break;
        }
    }
}
