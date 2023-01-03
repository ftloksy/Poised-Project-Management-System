import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ListenerLab implements ActionListener {
    TabFrame mainFrame;
    
    ListenerLab(TabFrame motherFrame){
        this.mainFrame = motherFrame ;
    }

    public void actionPerformed(ActionEvent e)
    {
        int tabIndex = this.mainFrame.demo.tabbedPane.getSelectedIndex();
        String surNameText = this.mainFrame.demo.personInsert.idText.getText();
        this.mainFrame.PosieDatabaseMenu.listAllItem.setText("Lab Test " + tabIndex + " " + surNameText );
        
    }
}
