import java.awt.*;
import javax.swing.*;

public class ButtonCard extends JPanel {
    final static int extraWindowWidth = 100;
    
    ButtonCard() {
        super();
        //super.add(new JTextField("TextField", 20));
        super.add(new JButton("Button 1"));
        super.add(new JButton("Button 2"));
        super.add(new JButton("Button 3"));
    }
    
    //Make the panel wider than it really needs, so
    //the window's wide enough for the tabs to stay
    //in one row.
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += this.extraWindowWidth;
        return size;
    }
    
}
