package Listener;
import javax.swing.*;
import javax.swing.event.*;

//import Main;
import Frame.PmsFrame;
import Handler.MenuHandler;

/** 
 * When User click the PmsTab and change the Page, Program will trigger this class. 
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      Main
 */
public class TabChangeListener implements ChangeListener {
    PmsFrame mainFrame;
    MenuHandler dbMenu;

    /** 
	 * TabChangeListener constructor 
	 * 
	 * @param motherFrame the main Frame ( Root Frame )
	 */ 
    public TabChangeListener(PmsFrame motherFrame) {
        super();
        this.mainFrame = motherFrame;
	    this.dbMenu = motherFrame.dbMenu;
    }

	/** Modify the Menu button's label */
    public void stateChanged(ChangeEvent changeEvent) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
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
				this.mainFrame.pmsTab.projectTab.tabbedPane.setSelectedIndex(0);
				this.dbMenu.reNewMenu();
				this.mainFrame.pmsTab.projectTab.projectEditor.updatePersonList();
				break;
		}
    }
}
