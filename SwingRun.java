/** 
 * run Swing ( GUI ) for database handle .
 * PmsFrame is the main frame.   
 * MysqlHandler is the database connection and query.
 * pass it to JFrame ( Root Frame ) for the GUI query. 
 */
public class SwingRun implements Runnable {
    public void run() {
            MysqlHandler dbHandler = new MysqlHandler();
            PmsFrame mainFrame = new PmsFrame(dbHandler);
            mainFrame.pack();
    }
}