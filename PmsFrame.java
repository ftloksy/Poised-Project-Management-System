import java.awt.*;
import javax.swing.*;

/** 
 * The Main Frame have a JTabbedPane PmsTab
 * every editor tab include editor and display JTable inside PmsTab.
 * 
 * MenuHandler have more button, handle action request.
 * e.g. Search Record, Enter Record, Update Record, Delete Record.
 * 
 * msgArea is a message notice.
 * programming will use msgArea to share information to client.
 */
public class PmsFrame extends JFrame {
    PmsTab pmsTab; 
    MenuHandler dbMenu ;
    JTextArea msgArea ;
    
    /**
     * param dbPosie  it care the PoisePMS database query and insert and update.
     */
    PmsFrame(MysqlHandler dbPoise) {
        super();

        this.msgArea = new JTextArea(3, 40);
        this.msgArea.setEditable(false);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        this.dbMenu = new MenuHandler(this, dbPoise);
        super.setJMenuBar(this.dbMenu);
        this.pmsTab = new PmsTab(this, dbPoise);
        this.pmsTab.addComponentToPane(super.getContentPane());

        this.dbMenu.reNewMenu();
        
        super.add( this.msgArea, BorderLayout.SOUTH );
        super.setPreferredSize(new Dimension(1800, 900));
        super.setVisible(true);
    }
}
