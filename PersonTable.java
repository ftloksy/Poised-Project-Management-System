import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

/*
 * PersonTable ( JTable ) will to Display 
 * Person table's information. 
 * When user click the table row, it will trigger PersonTableSelect .
 */
public class PersonTable extends JTable {
    DefaultTableModel personModel;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    PersonTableSelect personTbSelect ;
    PmsFrame mainFrame;
    
    PersonTable(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        createTableHeader();
        createTable();
    }
    
    /* Create the Table Header */
    void createTableHeader() {
        Vector<String> headerTitle = new Vector<String>();
        headerTitle.add("id");
        headerTitle.add("FirstName");
        headerTitle.add("SurName");
        headerTitle.add("Telephone");
        headerTitle.add("EmailAddress");
        headerTitle.add("PhysicalAddress");
        this.tableHeader = headerTitle ;
    }
    
    /* 
     * When the user search Person database table, 
     * then the table will display less record.
     * Program can use this method 
     * to display all record on table again.
     */
    void flashTable () {
        reNewTable ( this.dbHandler.selectPersonRecord() );
    }
   
    /*
     * Program can use this method to do searching.
     * flashTable method is a example.
     */    
    void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.personModel.setDataVector( dbRow , this.tableHeader );
    }
    
    void createTable() {
        
        this.personTbSelect = new PersonTableSelect(this.mainFrame);
        
        this.tableHeader = new Vector<String>();
        this.createTableHeader();

        /*
         * Disable the table edit function. 
         * User can select the row, but cannot edit.
         */
        this.personModel = new DefaultTableModel( this.dbHandler.selectPersonRecord() ,this.tableHeader) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            };
        };
        
        super.setModel(this.personModel);
        super.getSelectionModel().addListSelectionListener(this.personTbSelect);
    }
}
