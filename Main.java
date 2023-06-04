/** 
 * This is the main class of the program.
 * It serves as the entry point for execution.
 * The program creates an instance of the SwingRun class 
 * and invokes it using SwingUtilities.invokeLater().
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
     * It invokes the SwingRun instance using 
     * SwingUtilities.invokeLater().
     * 
     * @param args runtime string.
     */
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater( posieGUI );
    }
}
