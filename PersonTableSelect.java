import javax.swing.event.*;
import javax.swing.table.*;

public class PersonTableSelect implements ListSelectionListener {
    PmsFrame mainFrame ;
    PersonEditor personDbEditor;
    DefaultTableModel personModel;
    PersonTable personTable;
    
    PersonTableSelect(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }
    
    public void valueChanged(ListSelectionEvent e) {
        try {
            
            this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
            this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
            this.personModel = this.mainFrame.pmsTab.personTab.dbTable.personModel ;
            
            Integer selectIndex;
            selectIndex = this.personTable.getSelectedRow();
            this.personDbEditor.idText.setText( 
                (String)this.personModel.getValueAt(selectIndex, 0) );
            this.personDbEditor.firstNameText.setText( 
                (String)this.personModel.getValueAt(selectIndex, 1) );
            this.personDbEditor.surNameText.setText( (
                String)this.personModel.getValueAt(selectIndex, 2) );
            this.personDbEditor.telephoneText.setText( 
                (String)this.personModel.getValueAt(selectIndex, 3) );
            this.personDbEditor.emalAddressText.setText( 
                (String)this.personModel.getValueAt(selectIndex, 4) );
            this.personDbEditor.physicalAddressText.setText( 
                (String)this.personModel.getValueAt(selectIndex, 5) );
        } catch (ArrayIndexOutOfBoundsException g){
            this.personDbEditor.resetField();
        }
    }
}
