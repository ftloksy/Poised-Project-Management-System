import javax.swing.*;
import javax.swing.event.*;

public class ProjectTabChangeListener implements ChangeListener {
    PmsFrame mainFrame;
    MenuHandler dbMenu;

    
    ProjectTabChangeListener(PmsFrame motherFrame) {
        super();
        this.mainFrame = motherFrame;
	    this.dbMenu = motherFrame.dbMenu;
    }

    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
		switch ( index ) {

			case 0:
				this.dbMenu.clearItem.setText("Clear Project List");
				this.dbMenu.enterItem.setText("Enter Project Record");
				this.dbMenu.updateItem.setText("Update Project Record");
				this.dbMenu.deleteItem.setText("Delete Project Record");
				this.dbMenu.searchItem.setText("Search Project Record");
				this.dbMenu.listAllItem.setText("List All Project in List");
				this.dbMenu.reNewMenu();
				this.mainFrame.pmsTab.projectTab.projectEditor.updatePersonList();
                this.mainFrame.pmsTab.projectTab.refleshView();
				break;
            
            case 1:
                this.dbMenu.finalisedMenu(); 
                this.mainFrame.pmsTab.projectTab.refleshView();
				break;

            case 2:
                this.dbMenu.pastDueDateMenu();
                this.mainFrame.pmsTab.projectTab.refleshView();
                break;

            case 3:
                this.dbMenu.searchByProjectNumberMenu();
                this.mainFrame.pmsTab.projectTab.refleshView();
                break;
		}
    }
}
