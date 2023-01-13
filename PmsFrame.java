import java.awt.*;
import javax.swing.*;

public class PmsFrame extends JFrame {
    PmsTab pmsTab;
    MenuHandler dbMenu ;
    JTextArea msgArea ;
    
    PmsFrame(MysqlHandler dbPosie) {
        super();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        this.dbMenu = new MenuHandler(this, dbPosie);
        this.pmsTab = new PmsTab(this, dbPosie);
        this.pmsTab.addComponentToPane(super.getContentPane());
        super.setJMenuBar(this.dbMenu);
        this.dbMenu.reNewMenu();

    
        
        this.msgArea = new JTextArea(3, 40);
        this.msgArea.setEditable(false);
        
        super.add( this.msgArea, BorderLayout.SOUTH );
        super.setPreferredSize(new Dimension(1800, 900));
        super.setVisible(true);
    }
}
