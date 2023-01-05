import java.awt.*;
import javax.swing.*;

public class TabFrame extends JFrame {
    TabDemo demo;
    MenuHandler PosieDatabaseMenu ;
    JTextArea msgArea ;
    
    TabFrame(MysqlHandler dbPosie) {
        super();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        this.demo = new TabDemo(this, dbPosie);
        this.demo.addComponentToPane(super.getContentPane());
        PosieDatabaseMenu = new MenuHandler(this, dbPosie);
        super.setJMenuBar(PosieDatabaseMenu);
        
        this.msgArea = new JTextArea(3, 40);
        this.msgArea.setEditable(false);
        
        super.add( this.msgArea, BorderLayout.SOUTH );
        super.pack();
        super.setVisible(true);
    }
}
