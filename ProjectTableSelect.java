import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.* ;

public class ProjectTableSelect implements ListSelectionListener {
    PmsFrame mainFrame ;
    ProjectEditor dbEditor;
    DefaultTableModel dbModel;
    ProjectTable dbTable;
    ProjectTab pTab;
    Integer selectIndex;

    ProjectTableSelect(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }
    
    Integer findBdgType() {
        return findIndex( 
            (String)this.dbModel.getValueAt(this.selectIndex, 2) ,
            this.mainFrame.pmsTab.projectTab.bdgTypeList
            );
    }
    
    Integer findIndex(String targetTxt, DefaultListModel<String> checkModel) {
        for (int i = 0 ; i < checkModel.size() ; i ++ ) {
            //System.out.println( targetTxt + " : " + checkModel.get(i) );
            if ( checkModel.get(i).indexOf(targetTxt) > -1 ) {
                return i;
            }
        }
        return -1;
    }

    public void valueChanged(ListSelectionEvent e) {
        try {

            this.pTab = this.mainFrame.pmsTab.projectTab ;
            this.dbEditor = this.pTab.dbEditor ;
            this.dbTable = this.pTab.dbTable ;
            this.dbModel = this.pTab.dbTable.dbModel ;
            this.selectIndex = this.dbTable.getSelectedRow();
            
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
            
            this.pTab.bdgType.setSelectedIndex( this.findBdgType() );
        } catch (ArrayIndexOutOfBoundsException g){
            this.dbEditor.resetField();
        }
    }
}
