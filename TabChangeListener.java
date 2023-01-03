import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabChangeListener implements ChangeListener {
    JFrame mainFrame;
    
    TabChangeListener(JFrame motherFrame) {
        super();
        this.mainFrame = motherFrame;
    }

    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                break;
            case 1:
                break;
        }
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
        System.out.println("Tab changed to [INDEX]: " + index);
    }
}
