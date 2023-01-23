/** 
 * This is the running point. 
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         SwingRun
 */
public class Main {
    static SwingRun posieGUI = new SwingRun();

    /**
     * Entry Point
     * 
     * @param args runtime string.
     */
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater( posieGUI );
    }
}
