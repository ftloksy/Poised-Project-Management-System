import javax.swing.event.*;
import java.awt.event.*;
import java.sql.* ;

public class ListenerEnter implements ActionListener {
    TabFrame mainFrame;
    MysqlHandler dbHandler;
    
    ListenerEnter(TabFrame motherFrame, MysqlHandler dbPosie){
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
                    this.dbHandler.insertPersonRecord(
                        this.mainFrame.demo.personInsert.dbEditor.firstNameText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.surNameText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.telephoneText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.emalAddressText.getText(),
                        this.mainFrame.demo.personInsert.dbEditor.physicalAddressText.getText()
                    );
                    this.mainFrame.msgArea.setText("INSERT is Success.");
                    this.mainFrame.demo.personInsert.dbEditor.resetField();
                    this.mainFrame.demo.personInsert.dbTable.flashTable();
                } catch (SQLException pe) {
                    this.mainFrame.msgArea.setText(pe.getMessage());
                }
                //this.mainFrame.demo.personInsert.dbEditor.firstNameText.setText("FirstName");
                //this.mainFrame.demo.personInsert.dbEditor.surNameText.setText("SurName");
                //this.mainFrame.demo.personInsert.dbEditor.telephoneText.setText("Telephone");
                //this.mainFrame.demo.personInsert.dbEditor.emalAddressText.setText("EmailAddress");
                //this.mainFrame.demo.personInsert.dbEditor.physicalAddressText.setText("Address");
                
                //this.mainFrame.demo.personInsert.dbTable.flashTable();
                break;
            case 1:
                break;
        }
    }
}
