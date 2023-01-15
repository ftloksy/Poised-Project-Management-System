import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.* ;

public class ProjectTableSelect implements ListSelectionListener {
    PmsFrame mainFrame ;
    ProjectEditor dbEditor;
    FinalisedEditor finalisedEditor;
    DefaultTableModel dbModel;
    ProjectTable dbTable;
    ProjectTab pTab;
    Integer selectIndex;
    JTabbedPane tabPane;

    ProjectTableSelect(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }
    
    Integer findBdgType() {
        return findIndex( 
            (String)this.dbModel.getValueAt(this.selectIndex, 2) ,
            this.dbEditor.bdgTypeList
            );
    }
    
    Integer findIndex(String targetTxt, DefaultComboBoxModel<String> checkModel) {
        for (int i = 0 ; i < checkModel.getSize() ; i ++ ) {
            if ( checkModel.getElementAt(i).indexOf(targetTxt) > -1 ) {
                return i;
            }
        }
        return -1;
    }
    
    Integer findIndex(String targetTxt, DefaultListModel<String> checkModel) {
        for (int i = 0 ; i < checkModel.size() ; i ++ ) {
            if ( checkModel.getElementAt(i).indexOf(targetTxt) > -1 ) {
                return i;
            }
        }
        return -1;
    }

    public void valueChanged(ListSelectionEvent e) {
        this.tabPane = this.mainFrame.pmsTab.tabbedPane ;
        // int index = this.tabPane.getSelectedIndex();
        this.pTab = this.mainFrame.pmsTab.projectTab ;
        this.dbEditor = this.pTab.dbEditor ;
        this.finalisedEditor = this.mainFrame.pmsTab.finalisedTab.dbEditor;
        this.dbTable = this.pTab.dbTable ;
        this.dbModel = this.pTab.dbTable.dbModel ;
        this.selectIndex = this.dbTable.getSelectedRow();
        // switch ( index ) {
        //     case 1:       
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
                    
                } catch (ArrayIndexOutOfBoundsException g){
                    this.dbEditor.resetField();
                }
        //         break;
        //     case 2:
        //         String reportError ;
        //         reportError = "";
        //         try {
        //             reportError += (String)this.dbModel.getValueAt(this.selectIndex, 0) ;

        //             this.finalisedEditor.projectNoText.setText(
        //                 (String)this.dbModel.getValueAt(this.selectIndex, 0));

        //             reportError += (String)this.dbModel.getValueAt(this.selectIndex, 1);

        //             this.finalisedEditor.projectNameText.setText(
        //                 (String)this.dbModel.getValueAt(this.selectIndex, 1));

        //             reportError += (String)this.dbModel.getValueAt(this.selectIndex, 14);

        //             this.finalisedEditor.completedDateText.setText(
        //                     (String)this.dbModel.getValueAt(this.selectIndex, 14));

        //         } catch (ArrayIndexOutOfBoundsException g){
        //             this.dbEditor.resetField();
        //         }

        //         this.mainFrame.msgArea.setText( reportError );
                
        //         break;
		// }
    }
}
