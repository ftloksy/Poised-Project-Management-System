import Frame.PmsFrame;
import Handler.MysqlHandler;

/** 
 * This class represents the entry point for running the 
 * Swing-based graphical user interface (GUI) for handling a database.
 * It creates an instance of the MysqlHandler class 
 * for database connection and query handling.
 * It also creates an instance of the PmsFrame class, 
 * which serves as the main frame of the GUI.
 * The MysqlHandler instance is passed to the PmsFrame instance 
 * for performing database queries through the GUI.
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      Main
 */
public class SwingRun implements Runnable {
    public void run() {
            MysqlHandler dbHandler = new MysqlHandler();
            PmsFrame mainFrame = new PmsFrame(dbHandler);
            mainFrame.pack();
    }
}
