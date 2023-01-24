import javax.swing.*;
import javax.swing.table.*;

import java.sql.SQLException;
import java.util.Vector ;

/**
 * ProjectTable ( JTable ) will to Display 
 * Project table's information. 
 * When user click the table row, it will trigger ProjectTableSelect .
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      ProjectTableSelect
 * @see      MysqlHandler
 */
public class ProjectTable extends JTable {
    DefaultTableModel dbModel;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    ProjectTableSelect projectTbSelect ;
    PmsFrame mainFrame;
    
    /** 
     * ProjectTable constructor 
     * 
     * @param motherFrame the main Frame ( Root Frame )
     * @param dbPosie     the DatabaseHandler.
     */
    public ProjectTable(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        createTableHeader();
        createTable();
    }
    
    /** Create the Table Header */
    public void createTableHeader() {
        Vector<String> headerTitle = new Vector<String>();
        headerTitle.add("ProjectNumber");
        headerTitle.add("ProjectName");
        headerTitle.add("BuildingType");
        headerTitle.add("PhysicalAddress");
        headerTitle.add("ERFNumber");
        headerTitle.add("FeeCharged");
        headerTitle.add("PaidToDate");
        headerTitle.add("Deadline");
        
        headerTitle.add("ArchitectPId");
        headerTitle.add("ContractorPId");
        headerTitle.add("CustomerPId");
        headerTitle.add("ProjectManagerPId");
        headerTitle.add("StructuralEngineerPId");
        headerTitle.add("Finalised");
        headerTitle.add("CompletedDate");
        this.tableHeader =  headerTitle;
    }

    /** 
     * When the user search Project database table, 
     * then the table will display less record.
     * Program can use this method 
     * to display all record on table again.
     */ 
    public void flashTable () throws SQLException {
        reNewTable ( this.dbHandler.selectProjectRecord() );
    }
    
    /**
     * Program can use this method to do searching.
     * flashTable method is a example.
     * 
     * @param dbRow this is a query result from sql server. 
     *     MysqlHandler very format the result to Vector -) Vector - String ,
     */     
    public void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.dbModel.setDataVector( dbRow , this.tableHeader );
    }

    /** 
     * Find What Record haven't finalised. 
     * 
     * @throws SQLException If the database cannot connection or query at database or table. 
     */
    public void needCompleted() throws SQLException {
        reNewTable ( this.dbHandler.selectNeedCompletedProjectRecord() );
    }

    /** 
     * Follow the compledDate to find 
     * what Project's Deadline is early then the completedDate 
     * and hasn't finalised . 
     *  
     * @param completedDate the Project CompletedDate.
     * @throws SQLException If the database cannot connection or query at database or table. 
     */
    public void pastDueDate(String completedDate) throws SQLException {
        reNewTable ( this.dbHandler.selectPastDueDate(completedDate) );
    }
    
    /** Create the Project JTable */
    public void createTable() {
        
        this.projectTbSelect = new ProjectTableSelect(this.mainFrame);
        
        this.tableHeader = new Vector<String>();
        this.createTableHeader();

        /*
         * Disable the table edit function. 
         * User can select the row, but cannot edit.
         */        
        try {
            this.dbModel = new DefaultTableModel( this.dbHandler.selectProjectRecord() ,this.tableHeader) {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                };
            };
        } catch ( SQLException dbm) {
            Vector<Vector<String>> hasBug = new Vector<>();
            this.dbModel = new DefaultTableModel( hasBug, this.tableHeader) {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                };
            };
            this.mainFrame.msgArea.setText( dbm.getMessage() );
        }
        
        super.setModel(this.dbModel);
        super.getSelectionModel().addListSelectionListener(this.projectTbSelect);
    }
}
