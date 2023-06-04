package Listener;
import javax.swing.event.*;
import javax.swing.table.*;

import Editor.PersonEditor;
import Frame.PmsFrame;
import Table.PersonTable;

/**
 * This is Person Table's Listener, 
 * when user click the table's row, It will trigger it.
 * 
 * @author     Frankie Chow
 * @version    2023-1-23
 * @see        PersonTable
 * @see        PersonEditor
 */
public class PersonTableSelect implements ListSelectionListener {
    PmsFrame mainFrame ;
    PersonEditor personDbEditor;
    DefaultTableModel personModel;
    PersonTable personTable;
    
    /**
     * PersonTableSelect constructor
     * 
     * It will update the PersonTable JTable
     * and List all record in JTables.
     * 
     * @param motherFrame the main Frame ( Root Frame )
     */   
    public PersonTableSelect(PmsFrame motherFrame){
        this.mainFrame = motherFrame ;
    }
    
    /** When User choice a row, it will update the PersonEditor */
    public void valueChanged(ListSelectionEvent e) {
        try {
            
            this.personDbEditor = this.mainFrame.pmsTab.personTab.dbEditor ;
            this.personTable = this.mainFrame.pmsTab.personTab.dbTable ;
            this.personModel = this.mainFrame.pmsTab.personTab.dbTable.personModel ;
            
            /* Follow the Table row's data to update the Person Editor. */
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
