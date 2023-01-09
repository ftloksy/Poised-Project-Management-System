public class SwingRun implements Runnable {
    public void run() {
            MysqlHandler dbHandler = new MysqlHandler();
            PmsFrame mainFrame = new PmsFrame(dbHandler);
    }
}
