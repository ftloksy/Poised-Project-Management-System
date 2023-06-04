package Frame;
import java.awt.*;
import javax.swing.*;

import Handler.MenuHandler;
import Handler.MysqlHandler;
import Tab.PmsTab;

/** 
 * The PmsFrame class represents the main frame of the GUI.
 *
 * It contains a JTabbedPane called pmsTab, 
 * which includes editor tabs with editors and display JTables.
 *
 * The MenuHandler class handles various action requests through buttons,
 * such as searching, entering, updating, and deleting records.
 *
 * The msgArea is a text area used for displaying message 
 * notices and sharing information with the client.
 * 
 * @author     Frankie Chow
 * @version    2023-1-23
 */
public class PmsFrame extends JFrame {

    /**
     * The pmsTab is a JTabbedPane that contains 
     * PersonTab for handling Person data table
     * and ProjectTab for handling Project data table.
     */
    public PmsTab pmsTab; 

    /**
     * The dbMenu is an instance of the MenuHandler class
     * which handles various action requests.
     * It includes buttons for actions such as 
     * searching, entering, updating, and deleting records.
     */
    public MenuHandler dbMenu ;

    /**
     * The msgArea is a JTextArea used for displaying message notices.
     * It is used for sharing information with the client.
     */
    public JTextArea msgArea ;
    
    /**
     * PmsFrame constructor
     * 
     * @param dbPoise  it care the PoisePMS database query and insert and update.
     */
    public PmsFrame(MysqlHandler dbPoise) {
        super();

        // Create and configure the msgArea
        this.msgArea = new JTextArea(3, 40);
        this.msgArea.setEditable(false);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());

        // Create and set the dbMenu as the JMenuBar
        this.dbMenu = new MenuHandler(this, dbPoise);
        super.setJMenuBar(this.dbMenu);

        // Create the pmsTab and add it to the content pane
        this.pmsTab = new PmsTab(this, dbPoise);
        this.pmsTab.addComponentToPane(super.getContentPane());

        this.dbMenu.reNewMenu();
        
        // Add the msgArea to the SOUTH position of the BorderLayout
        super.add( this.msgArea, BorderLayout.SOUTH );
        super.setPreferredSize(new Dimension(1800, 900));
        super.setVisible(true);
    }
}
