import javax.swing.*;
import java.awt.event.*;

public class ListenerClear implements ActionListener {
    PmsFrame mainFrame;
    JTabbedPane tabPane;
    PersonEditor personDbEditor;
    ProjectEditor projectDbEditor;
    
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
                this.projectDbEditor = this.mainFrame.pmsTab.projectTab.projectEditor ;
                this.mainFrame.msgArea.setText("Clear Tab 1");
                this.projectDbEditor.resetField();
                this.projectDbEditor.updatePersonList();
                this.projectDbEditor.bdgType.setSelectedItem("");
                break;
        }
    }
}
