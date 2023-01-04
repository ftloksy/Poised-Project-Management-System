public class SwingRun implements Runnable {
    public void run() {
            MysqlHandler dbHandler = new MysqlHandler();
            TabFrame tabframe = new TabFrame(dbHandler);
    }
}
