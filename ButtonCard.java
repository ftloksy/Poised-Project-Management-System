import java.awt.*;
import javax.swing.*;

public class ButtonCard extends JPanel {
    final static String week[] = { "Monday","Tuesday",
        "Wednesday", "Thursday","Friday","Saturday","Sunday", "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh"} ;
    final static int extraWindowWidth = 100;
    JScrollPane dbScroll ;
    JList b;
    
    ButtonCard() {
        super();
        //super.add(new JTextField("TextField", 20));
        //super.add(new JButton("Button 1"));
        //super.add(new JButton("Button 2"));
        //super.add(new JButton("Button 3"));
        //String array to store weekdays
        //create list
        this.b = new JList<String>(this.week);
        //this.b.setVisibleRowCount(3);
        this.dbScroll = new JScrollPane( new JList<String>(this.week) );
        this.dbScroll.setRowHeaderView(new JLabel("List Lab"));
        
        super.add( this.dbScroll );
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
