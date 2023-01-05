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
            //System.out.println( (String)sqlTableModel.getValueAt(i, 1));
            this.mainFrame.demo.personInsert.firstNameText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 1) );
            this.mainFrame.demo.personInsert.surNameText.setText( (
                String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 2) );
            this.mainFrame.demo.personInsert.telephoneText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 3) );
            this.mainFrame.demo.personInsert.emalAddressText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 4) );
            this.mainFrame.demo.personInsert.physicalAddressText.setText( 
                (String)this.mainFrame.demo.personInsert.dbTable.personModel.getValueAt(selectIndex, 5) );
        } catch (ArrayIndexOutOfBoundsException g){
            this.mainFrame.demo.personInsert.firstNameText.setText( null );
            this.mainFrame.demo.personInsert.surNameText.setText( null );
            this.mainFrame.demo.personInsert.telephoneText.setText( null );
            this.mainFrame.demo.personInsert.emalAddressText.setText( null );
            this.mainFrame.demo.personInsert.physicalAddressText.setText( null );
        }
    }
}
