import javax.swing.event.*;
import java.awt.event.*;
import java.sql.* ;
import java.util.Vector;

public class ListenerListAll implements ActionListener {
    TabFrame mainFrame;
    MysqlHandler dbHandler;
    
    ListenerListAll(TabFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText("");
        int index = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:

                this.mainFrame.msgArea.setText("List all Person's Record.");
                this.mainFrame.demo.personInsert.dbEditor.resetField();
                this.mainFrame.demo.personInsert.dbTable.flashTable();

                break;
            case 1:
                break;
        }
    }
}
