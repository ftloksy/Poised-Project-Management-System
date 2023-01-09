import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.* ;

public class ListenerUpdate implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    PersonTable personTable;
    
    ListenerUpdate(PmsFrame motherFrame, MysqlHandler dbPosie){
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
                try {
                    this.dbHandler.updatePersonRecord(
                        this.personDbEditor.idText.getText(),
                        this.personDbEditor.firstNameText.getText(),
                        this.personDbEditor.surNameText.getText(),
                        this.personDbEditor.telephoneText.getText(),
                        this.personDbEditor.emalAddressText.getText(),
                        this.personDbEditor.physicalAddressText.getText()
                    );
;
                    this.mainFrame.msgArea.setText("Update is Success.");
                    this.personDbEditor.resetField();
                    this.personTable.flashTable();
                } catch (SQLException pe) {
                    String sqlCode = pe.getSQLState();
                    if ( sqlCode.equals("22001") ) {
                        this.mainFrame.msgArea.setText(
                             "If you want to delete a record. Please select a record in the table." );
                    } else {
                        this.mainFrame.msgArea.setText(pe.getMessage());
                    }
                }
                break;
            case 1:
                break;
        }
    }
}
