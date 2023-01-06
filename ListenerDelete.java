import javax.swing.event.*;
import java.awt.event.*;
import java.sql.* ;

public class ListenerDelete implements ActionListener {
    TabFrame mainFrame;
    MysqlHandler dbHandler;
    
    ListenerDelete(TabFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame ;
        this.dbHandler = dbPosie ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText("");
        int index = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                String id = this.mainFrame.demo.personInsert.dbEditor.idText.getText();
                if ( !id.equals("Id") ) {
                    try {
                        this.dbHandler.deletePerson( id );
                        this.mainFrame.msgArea.setText("Delete is Success.");
                        this.mainFrame.demo.personInsert.dbEditor.resetField();
                        this.mainFrame.demo.personInsert.dbTable.flashTable();
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
