import javax.swing.*;
import javax.swing.table.*;

import java.sql.SQLException;
import java.util.Vector ;

/**
 * PersonTable ( JTable ) will to Display 
 * Person table's information. 
 * When user click the table row, it will trigger PersonTableSelect .
 * 
 * @author    Frankie Chow
 * @version   2023-1-23
 * @see       PersonTable
 * @see       PersonEditor
 * @see       MysqlHandler
 */
public class PersonTable extends JTable {
    DefaultTableModel personModel;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    PersonTableSelect personTbSelect ;
    PmsFrame mainFrame;
    
    /**
     * PersonTable constructor
     * 
     * @param motherFrame   the main Frame ( Root Frame )
     * @param dbPosie       the DatabaseHandler.
     */
    public PersonTable(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        createTableHeader();
        createTable();
    }
    
    /** Create the Table Header */
    public void createTableHeader() {
        Vector<String> headerTitle = new Vector<String>();
        headerTitle.add("id");
        headerTitle.add("FirstName");
        headerTitle.add("SurName");
        headerTitle.add("Telephone");
        headerTitle.add("EmailAddress");
        headerTitle.add("PhysicalAddress");
        this.tableHeader = headerTitle ;
    }
    
    /**
     * When the user search Person database table, 
     * then the table will display less record.
     * Program can use this method 
     * to display all record on table again.
     * 
     * @throws SQLException If the database cannot connection or query at database or table. 
     */
    public void flashTable () throws SQLException {
        reNewTable ( this.dbHandler.selectPersonRecord() );
    }
   
    /**
     * Program can use this method to do searching.
     * flashTable method is a example.
     * 
     * @param dbRow    this is a query result from sql server. 
     *                 MysqlHandler very format the result to Vector<Vector<String>> ,
     */    
    public void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.personModel.setDataVector( dbRow , this.tableHeader );
    }
    
    /** Create Person Table. */
    public void createTable() {
        
        this.personTbSelect = new PersonTableSelect(this.mainFrame);
        
        this.tableHeader = new Vector<String>();
        this.createTableHeader();

        /*
         * Disable the table edit function. 
         * User can select the row, but cannot edit.
         */
        try {
            this.personModel = new DefaultTableModel( this.dbHandler.selectPersonRecord() ,this.tableHeader) {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                };
            };
        } catch ( SQLException spr ) {
            Vector<Vector<String>> hasBug = new Vector<>();
            this.personModel = new DefaultTableModel( hasBug, this.tableHeader) {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                };
            };
            this.mainFrame.msgArea.setText( spr.getMessage() );
        }
        
        super.setModel(this.personModel);
        super.getSelectionModel().addListSelectionListener(this.personTbSelect);
    }
}
