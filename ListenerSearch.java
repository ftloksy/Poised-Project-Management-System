import javax.swing.event.*;
import java.awt.event.*;
import java.sql.* ;
import java.util.Vector;

public class ListenerSearch implements ActionListener {
    TabFrame mainFrame;
    MysqlHandler dbHandler;
    
    ListenerSearch (TabFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText("");
        int index = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:

                Vector<Vector<String>> searchResult = this.dbHandler.searchPersonRecord(
                    this.mainFrame.demo.personInsert.dbEditor.firstNameText.getText(),
                    this.mainFrame.demo.personInsert.dbEditor.surNameText.getText(),
                    this.mainFrame.demo.personInsert.dbEditor.telephoneText.getText(),
                    this.mainFrame.demo.personInsert.dbEditor.emalAddressText.getText(),
                    this.mainFrame.demo.personInsert.dbEditor.physicalAddressText.getText()
                );
                this.mainFrame.msgArea.setText("Search is Complete.");
                this.mainFrame.demo.personInsert.dbEditor.resetField();
                this.mainFrame.demo.personInsert.dbTable.reNewTable( searchResult );

                break;
            case 1:
                break;
        }
    }
}
