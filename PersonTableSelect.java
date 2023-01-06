import javax.swing.event.*;

public class PersonTableSelect implements ListSelectionListener {
    TabFrame mainFrame ;
    
    PersonTableSelect(TabFrame motherFrame){
        this.mainFrame = motherFrame ;
    }
    
    public void valueChanged(ListSelectionEvent e) {
        try {
            Integer selectIndex;
            selectIndex = this.mainFrame.demo.personInsert.dbTable.getSelectedRow();
            this.mainFrame.demo.personInsert.dbEditor.idText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 0) );
            this.mainFrame.demo.personInsert.dbEditor.firstNameText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 1) );
            this.mainFrame.demo.personInsert.dbEditor.surNameText.setText( (
                String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 2) );
            this.mainFrame.demo.personInsert.dbEditor.telephoneText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 3) );
            this.mainFrame.demo.personInsert.dbEditor.emalAddressText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 4) );
            this.mainFrame.demo.personInsert.dbEditor.physicalAddressText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 5) );
        } catch (ArrayIndexOutOfBoundsException g){
            this.mainFrame.demo.personInsert.dbEditor.resetField();
        }
    }
}
