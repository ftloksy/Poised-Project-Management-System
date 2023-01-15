import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.* ;

public class FinalisedTableSelect implements ListSelectionListener {
// public class FinalisedTableSelect extends ProjectTableSelect {
    PmsFrame mainFrame ;
    FinalisedEditor dbEditor;
    FinalisedEditor finalisedEditor;
    DefaultTableModel dbModel;
    FinalisedTable dbTable;
    FinalisedTab fTab;
    Integer selectIndex;
    JTabbedPane tabPane;

    FinalisedTableSelect(PmsFrame motherFrame){
        // super(motherFrame);
        this.mainFrame = motherFrame ;
    }
    
    // Integer findIndex(String targetTxt, DefaultComboBoxModel<String> checkModel) {
    //     for (int i = 0 ; i < checkModel.getSize() ; i ++ ) {
    //         if ( checkModel.getElementAt(i).indexOf(targetTxt) > -1 ) {
    //             return i;
    //         }
    //     }
    //     return -1;
    // }
    
    // Integer findIndex(String targetTxt, DefaultListModel<String> checkModel) {
    //     for (int i = 0 ; i < checkModel.size() ; i ++ ) {
    //         if ( checkModel.getElementAt(i).indexOf(targetTxt) > -1 ) {
    //             return i;
    //         }
    //     }
    //     return -1;
    // }

    public void valueChanged(ListSelectionEvent e) {
        //this.mainFrame.msgArea.setText("");
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        //int index = this.tabPane.getSelectedIndex();
        this.fTab = this.mainFrame.pmsTab.finalisedTab ;
        this.dbEditor = this.fTab.dbEditor ;
        this.finalisedEditor = this.fTab.dbEditor;
        this.dbTable = this.fTab.dbTable ;
        this.dbModel = this.fTab.dbTable.dbModel ;
        this.selectIndex = this.dbTable.getSelectedRow();
		String reportError ;
		reportError = "";
		try {
			reportError += (String)this.dbModel.getValueAt(this.selectIndex, 0) ;

			this.finalisedEditor.projectNoText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 0));

			reportError += (String)this.dbModel.getValueAt(this.selectIndex, 1);

			this.finalisedEditor.projectNameText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 1));

			reportError += (String)this.dbModel.getValueAt(this.selectIndex, 14);

            String completedDate = (String)this.dbModel.getValueAt(this.selectIndex, 14);
            if ( completedDate == null ) {
		    	this.finalisedEditor.completedDateText.setText(java.time.LocalDate.now().toString());
            } else {
		    	this.finalisedEditor.completedDateText.setText(completedDate);
            }

		} catch (ArrayIndexOutOfBoundsException g){
			this.dbEditor.resetField();
		}

		this.mainFrame.msgArea.setText( reportError );
                
    }
}
