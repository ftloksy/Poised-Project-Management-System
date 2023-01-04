import java.awt.*;
import javax.swing.*;

public class TabFrame extends JFrame {
    TabDemo demo;
    MenuHandler PosieDatabaseMenu ;
    
    TabFrame(MysqlHandler dbPosie) {
        super();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.demo = new TabDemo(this, dbPosie);
        this.demo.addComponentToPane(super.getContentPane());
        PosieDatabaseMenu = new MenuHandler(this, dbPosie);
        super.setJMenuBar(PosieDatabaseMenu);
        super.pack();
        super.setVisible(true);
    }
}
