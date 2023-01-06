import javax.swing.event.*;
import java.awt.event.*;
import java.sql.* ;

public class ListenerUpdate implements ActionListener {
    TabFrame mainFrame;
    MysqlHandler dbHandler;
    
    ListenerUpdate(TabFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText("");
        int index = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                try {
                    this.dbHandler.updatePersonRecord(
                        this.mainFrame.demo.personInsert.dbEditor.idText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.firstNameText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.surNameText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.telephoneText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.emalAddressText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.physicalAddressText.getText()
                    );
;
                    this.mainFrame.msgArea.setText("Update is Success.");
                    this.mainFrame.demo.personInsert.dbEditor.resetField();
                    this.mainFrame.demo.personInsert.dbTable.flashTable();
                } catch (SQLException pe) {
                    String sqlCode = pe.getSQLState();
                    if ( sqlCode.equals("22001") ) {
                        this.mainFrame.msgArea.setText(
                             "If you want to delete a record. Please select a record in the table." );
                    } else {
                        this.mainFrame.msgArea.setText(pe.getMessage());
                    }
                    
                    //this.mainFrame.msgArea.setText(pe.getMessage());
                    //this.mainFrame.msgArea.setText(pe.getSQLState());
                }
                break;
            case 1:
                break;
        }
    }
}
