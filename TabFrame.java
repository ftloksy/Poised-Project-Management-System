import java.awt.*;
import javax.swing.*;

public class TabFrame extends JFrame {
    TabDemo demo;
    MenuHandler PosieDatabaseMenu ;
    
    TabFrame() {
        super();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.demo = new TabDemo(this);
        this.demo.addComponentToPane(super.getContentPane());
        PosieDatabaseMenu = new MenuHandler(this);
        super.setJMenuBar(PosieDatabaseMenu);
        super.pack();
        super.setVisible(true);
    }
}
