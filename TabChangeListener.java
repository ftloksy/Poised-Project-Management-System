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
        if ( this.mainFrame.menuEnable ) {
            switch ( index ) {
                case 0:
                    this.dbMenu.clearItem.setText("Clear Person List");
                    this.dbMenu.enterItem.setText("Enter Person Record");
                    this.dbMenu.updateItem.setText("Update Person Record");
                    this.dbMenu.deleteItem.setText("Delete Person Record");
                    this.dbMenu.searchItem.setText("Search Person Record");
                    this.dbMenu.listAllItem.setText("List All Person in List");
                    this.dbMenu.reNewMenu();
                    break;
                case 1:
                    this.dbMenu.clearItem.setText("Clear Project List");
                    this.dbMenu.enterItem.setText("Enter Project Record");
                    this.dbMenu.updateItem.setText("Update Project Record");
                    this.dbMenu.deleteItem.setText("Delete Project Record");
                    this.dbMenu.searchItem.setText("Search Project Record");
                    this.dbMenu.listAllItem.setText("List All Project in List");
                    this.dbMenu.reNewMenu();
                    this.mainFrame.pmsTab.projectTab.dbEditor.updatePersonList();
                    break;
                case 2:
                    this.dbMenu.removeAll();
                    this.dbMenu.finalisedMenu();
                    this.dbMenu.listAllItem.setText("List All Project in List");
                    break;
            }
        }
    }
}
