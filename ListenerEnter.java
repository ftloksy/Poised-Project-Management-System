import java.awt.*;
import javax.swing.*;
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
        int index = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                try {
                    this.dbHandler.insertPersonRecord(
                        this.mainFrame.demo.personInsert.firstNameText.getText(),
                        this.mainFrame.demo.personInsert.surNameText.getText(),
                        this.mainFrame.demo.personInsert.telephoneText.getText(),
                        this.mainFrame.demo.personInsert.emalAddressText.getText(),
                        this.mainFrame.demo.personInsert.physicalAddressText.getText()
                    );
                } catch (SQLException pe) {
                    this.mainFrame.msgArea.setText(pe.getMessage());
                }
                this.mainFrame.demo.personInsert.firstNameText.setText("FirstName");
                this.mainFrame.demo.personInsert.surNameText.setText("SurName");
                this.mainFrame.demo.personInsert.telephoneText.setText("Telephone");
                this.mainFrame.demo.personInsert.emalAddressText.setText("EmailAddress");
                this.mainFrame.demo.personInsert.physicalAddressText.setText("Address");
                
                this.mainFrame.demo.personInsert.dbTable.flashTable();
                break;
            case 1:
                break;
        }
    }
}
