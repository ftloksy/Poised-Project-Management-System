import javax.swing.*;
import javax.swing.event.*;

public class TabChangeListener implements ChangeListener {
    PmsFrame mainFrame;
    MenuHandler dbMenu;

    
    TabChangeListener(PmsFrame motherFrame) {
        super();
        this.mainFrame = motherFrame;
	    this.dbMenu = motherFrame.dbMenu;
    }

    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        switch ( index ) {
            case 0:
                this.dbMenu.clearItem.setText("Clear Person");
                this.dbMenu.enterItem.setText("Enter Person");
                this.dbMenu.updateItem.setText("Update Person");
                this.dbMenu.deleteItem.setText("Delete Person");
                this.dbMenu.searchItem.setText("Search Person");
                this.dbMenu.listAllItem.setText("ListAll Person");
                break;
            case 1:
                this.dbMenu.clearItem.setText("Clear Poised");
                this.dbMenu.enterItem.setText("Enter Poised");
                this.dbMenu.updateItem.setText("Update Poised");
                this.dbMenu.deleteItem.setText("Delete Poised");
                this.dbMenu.searchItem.setText("Search Poised");
                this.dbMenu.listAllItem.setText("ListAll Poised");
                this.mainFrame.pmsTab.projectTab.dbEditor.updatePersonList();
                break;
        }
    }
}
