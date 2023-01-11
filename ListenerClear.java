import javax.swing.*;
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
        this.mainFrame.msgArea.setText("");
        int index = this.tabPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
                //int tabIndex = this.tabPane.getSelectedIndex();
                this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
                this.personDbEditor.resetField();
                this.mainFrame.msgArea.setText("");
                break;
            case 1:
                this.mainFrame.msgArea.setText("Clear Tab 1");
                this.mainFrame.pmsTab.projectTab.dbEditor.resetField();
                this.mainFrame.pmsTab.projectTab.updatePersonList();
                this.mainFrame.pmsTab.projectTab.bdgType.setSelectedItem("");
                break;
        }
    }
}
