package Listener;
import javax.swing.event.*;
import javax.swing.table.*;

import Editor.FinalisedEditor;
import Editor.ProjectEditor;
import Frame.PmsFrame;
import Tab.ProjectTab;
import Table.ProjectTable;

import javax.swing.* ;

/**
 * This is Project Table's Listener, 
 * when user click the table's row, It will trigger it.
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      ProjectTable
 * @see      ProjectEditor
 */
public class ProjectTableSelect implements ListSelectionListener {
    PmsFrame mainFrame ;
    ProjectEditor dbEditor;
    FinalisedEditor finalisedEditor;
    DefaultTableModel dbModel;
    ProjectTable dbTable;
    ProjectTab projectTab;
    Integer selectIndex;
    JTabbedPane tabPane;

	/**
	 *  ProjectTableSelect constructor 
	 * 
	 * @param motherFrame  the main Frame ( Root Frame )
	 */
    public ProjectTableSelect(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }

	/** 
	 * use ProjectTable's bdgType column to find in 
	 * use ProjectEditor's bdgType JComboBox's information
	 * find the target String's Model index.
	 * 
	 * This will call findIndex to find it.
	 * 
	 * @return the index if found, if cannot find return -1.
	 */
    public Integer findBdgType() {
        return findIndex( 
            (String)this.dbModel.getValueAt(this.selectIndex, 2) ,
            this.dbEditor.bdgTypeList
            );
    }

	/** 
	 * this is match method.
	 * Use the targetTxt to find in DefaultComboBoxModel's List.
	 * return the match content's index.
	 * 
	 * @param targetTxt    the finding target String.
	 * @param checkModel   find in checkModel contents
	 * @return             the index, If find return the index, 
	 *                     if cannot find return -1.
	 */
    public Integer findIndex(String targetTxt, DefaultComboBoxModel<String> checkModel) {
        for (int i = 0 ; i < checkModel.getSize() ; i ++ ) {
            if ( checkModel.getElementAt(i).indexOf(targetTxt) > -1 ) {
                return i;
            }
        }
        return -1;
    }
    
	/**  Reset the Project Editor */
    public void valueChanged(ListSelectionEvent e) {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        this.projectTab = this.mainFrame.pmsTab.projectTab ;
        this.dbEditor = this.projectTab.projectEditor ;
        this.finalisedEditor = this.projectTab.finalisedEditor;
        this.dbTable = this.projectTab.dbTable ;
        this.dbModel = this.projectTab.dbTable.dbModel ;
        this.selectIndex = this.dbTable.getSelectedRow();
		try {

			this.dbEditor.projectNoText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 0) );
			this.dbEditor.projectNameText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 1) );
			this.dbEditor.physicalAddressText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 3) );
			this.dbEditor.erfNoText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 4) );
			this.dbEditor.feeChargedText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 5) );
			this.dbEditor.paidTodateText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 6) );
			this.dbEditor.deadlineText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 7) );
			
			this.dbEditor.bdgType.setSelectedIndex( this.findBdgType() );
			
			this.dbEditor.setArchitect.setSelectedIndex(
				this.findIndex (
					(String)this.dbModel.getValueAt(this.selectIndex, 8),
					this.dbEditor.architectBoxModel )
			);

			this.dbEditor.setContractor.setSelectedIndex(
				this.findIndex (
					(String)this.dbModel.getValueAt(this.selectIndex, 9),
					this.dbEditor.contractorBoxModel )
			);

			this.dbEditor.setCustomer.setSelectedIndex(
				this.findIndex (
					(String)this.dbModel.getValueAt(this.selectIndex, 10),
					this.dbEditor.customerBoxModel )
			);
			
			this.dbEditor.setManager.setSelectedIndex(
				this.findIndex (
					(String)this.dbModel.getValueAt(this.selectIndex, 11),
					this.dbEditor.managerBoxModel )
			);

			this.dbEditor.setEngineer.setSelectedIndex(
				this.findIndex (
					(String)this.dbModel.getValueAt(this.selectIndex, 12),
					this.dbEditor.engineerBoxModel )
			);

			this.dbEditor.setFinalised.setSelectedItem(
				(String)this.dbModel.getValueAt(this.selectIndex, 13)
			);

			this.dbEditor.completedDateText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 14)
			);
			
			this.finalisedEditor.projectNoText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 0));


			this.finalisedEditor.projectNameText.setText(
				(String)this.dbModel.getValueAt(this.selectIndex, 1));

			/* 
			 * If the Project isn't finalised. the record hasn't CompletedDate.
			 * so set the completedDateText to currect date ( today ).
			 * If the Project is finalised. the record has CompletedDate .
			 * so set the completedDateText follow the record.
			 */
			String completedDateString = (String)this.dbModel.getValueAt(this.selectIndex, 14);
			if ( completedDateString ==  null) {
				this.finalisedEditor.completedDateText.setText( java.time.LocalDate.now().toString() );
			} else {
				this.finalisedEditor.completedDateText.setText( completedDateString );
			}
			
		} catch (ArrayIndexOutOfBoundsException g){
			this.dbEditor.resetField();
		}
    }
}
