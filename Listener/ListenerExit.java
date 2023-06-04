package Listener;
import java.awt.event.*;

import Handler.MysqlHandler;

/**
 * This is Listener for [Exit] Button, When User click the Button, 
 * will trigger this action.
 * The program will close sql statement and sql connection.
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         MysqlHandler 
 */
public class ListenerExit implements ActionListener {
    MysqlHandler dbHandler;

    /**
     * ListenerEnter constructor
     * 
     * @param dbPoise the Database Handler.
     */  
    public ListenerExit(MysqlHandler dbPoise ) {
        this.dbHandler = dbPoise ;
    }

    /** This action will less the program exist. */
    public void actionPerformed(ActionEvent e)
    {
        this.dbHandler.closeSQLConnectionAndStatement();
        System.exit(0);
    }
}
