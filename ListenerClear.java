import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ListenerClear implements ActionListener {
    TabFrame mainFrame;
    
    ListenerClear(TabFrame motherFrame){
        this.mainFrame = motherFrame ;
    }

    public void actionPerformed(ActionEvent e)
    {
        int tabIndex = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        this.mainFrame.demo.personInsert.dbEditor.resetField();
        this.mainFrame.msgArea.setText("");
        
    }
}
