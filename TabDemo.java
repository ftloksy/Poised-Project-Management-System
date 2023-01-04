import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabDemo {
    final static String PersonTabTilte = "Insert to Person";
    final static String BUTTONPANEL = "Tab with JButtons";
    TabChangeListener changeListener ;
    ButtonCard card1 = new ButtonCard();
    PersonInsert personInsert = new PersonInsert();
    TabFrame mainFrame;
    JTabbedPane tabbedPane ;
    
    TabDemo(TabFrame motherFrame) {
        this.mainFrame = motherFrame ;
        changeListener = new TabChangeListener(this.mainFrame);
    }
    
    public void addComponentToPane(Container pane) {
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab(PersonTabTilte, personInsert);
        tabbedPane.addTab(BUTTONPANEL, card1);
        
        tabbedPane.addChangeListener(changeListener);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}
