import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

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
                this.dbHandler.insertPersonRecord(
                    this.mainFrame.demo.personInsert.firstNameText.getText(),
                    this.mainFrame.demo.personInsert.surNameText.getText(),
                    this.mainFrame.demo.personInsert.telephoneText.getText(),
                    this.mainFrame.demo.personInsert.emalAddressText.getText(),
                    this.mainFrame.demo.personInsert.physicalAddressText.getText()
                );
                break;
            case 1:
                break;
        }
    }
}
