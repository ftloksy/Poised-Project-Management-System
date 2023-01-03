import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabChangeListener implements ChangeListener {
    TabFrame mainFrame;
    MenuHandler dbMenu ;
    
    TabChangeListener(TabFrame motherFrame) {
        super();
        this.mainFrame = motherFrame;
        //this.dbMenu = this.mainFrame.PosieDatabaseMenu ;
    }

    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                this.mainFrame.PosieDatabaseMenu.enterItem.setText("INDEXONE");
                break;
            case 1:
                this.mainFrame.PosieDatabaseMenu.enterItem.setText("INDEXTWO");
                break;
        }
        //System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
        //System.out.println("Tab changed to [INDEX]: " + index);
    }
}
