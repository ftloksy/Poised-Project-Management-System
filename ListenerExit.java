import java.awt.event.*;

/*
 * This is Listener for [Exit] Button, When User click the Button, 
 * will trigger this action.
 * The program will close sql statement and sql connection.
 */
public class ListenerExit implements ActionListener {
    MysqlHandler dbHandler;

    ListenerExit(MysqlHandler dbPoise ) {
        this.dbHandler = dbPoise ;
    }
    public void actionPerformed(ActionEvent e)
    {
        this.dbHandler.closeSQLConnectionAndStatement();
        System.exit(0);
    }
}
