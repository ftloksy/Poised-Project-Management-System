import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ListenerClear implements ActionListener {
    PmsFrame mainFrame;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    
    ListenerClear(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        int tabIndex = this.tabPane.getSelectedIndex();
        this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
        this.personDbEditor.resetField();
        this.mainFrame.msgArea.setText("");
    }
}
