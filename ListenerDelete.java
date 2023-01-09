import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;

public class ListenerDelete implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    
    ListenerDelete(PmsFrame motherFrame, MysqlHandler dbPosie){
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
                String id = this.personDbEditor.idText.getText();
                if ( !id.equals("Id") ) {
                    try {
                        this.dbHandler.deletePerson( id );
                        this.mainFrame.msgArea.setText("Delete is Success.");
                        this.personTable.flashTable();
                    } catch (SQLException pe) { 
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
            case 1:
                break;
        }
    }
}
