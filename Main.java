/* 
 * run Swing ( GUI ) for database handle .
 * PmsFrame is the main frame.   
 * MysqlHandler is the database connection and query.
 */
class SwingRun implements Runnable {
    public void run() {
            MysqlHandler dbHandler = new MysqlHandler();
            PmsFrame mainFrame = new PmsFrame(dbHandler);
            mainFrame.pack();
    }
}

/* This is the running point. */
public class Main {
    static SwingRun posieGUI = new SwingRun();
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater( posieGUI );
    }
}
