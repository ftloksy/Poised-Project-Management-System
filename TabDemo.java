import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabDemo {
    final static String BUTTONPANEL = "Tab with JButtons";
    final static String TEXTPANEL = "Insert to Person";
    TabChangeListener changeListener ;
    ButtonCard card1 = new ButtonCard();
    PersonInsert personInsert = new PersonInsert();
    TabFrame mainFrame;
    
    TabDemo(TabFrame motherFrame) {
        this.mainFrame = motherFrame ;
        changeListener = new TabChangeListener(this.mainFrame);
    }
    
    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab(BUTTONPANEL, card1);
        tabbedPane.addTab(TEXTPANEL, personInsert);
        
        tabbedPane.addChangeListener(changeListener);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}
