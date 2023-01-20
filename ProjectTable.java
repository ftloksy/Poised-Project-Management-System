import javax.swing.*;
import javax.swing.table.*;

import java.sql.SQLException;
import java.util.Vector ;

/*
 * ProjectTable ( JTable ) will to Display 
 * Project table's information. 
 * When user click the table row, it will trigger ProjectTableSelect .
 */
public class ProjectTable extends JTable {
    DefaultTableModel dbModel;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    ProjectTableSelect projectTbSelect ;
    PmsFrame mainFrame;
    
    ProjectTable(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        createTableHeader();
        createTable();
    }
    
    /* Create the Table Header */
    void createTableHeader() {
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

    /* 
     * When the user search Project database table, 
     * then the table will display less record.
     * Program can use this method 
     * to display all record on table again.
     */ 
    void flashTable () {
        try {
            reNewTable ( this.dbHandler.selectProjectRecord() );
        } catch ( SQLException spj ) {
           this.mainFrame.msgArea.setText( spj.getMessage() );
        }
    }
    
    /*
     * Program can use this method to do searching.
     * flashTable method is a example.
     */     
    void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.dbModel.setDataVector( dbRow , this.tableHeader );
    }

    /* Find What Record haven't finalised. */
    void needCompleted() {
        try {
            reNewTable ( this.dbHandler.selectNeedCompletedProjectRecord() );
        } catch ( SQLException sncpj ) {
            this.mainFrame.msgArea.setText( sncpj.getMessage() ) ;
        }
    }

    /* 
     * Follow the compledDate to find 
     * what Project's Deadline is early then the completedDate 
     * and hasn't finalised . 
     */
    void pastDueDate(String completedDate) throws SQLException {
        reNewTable ( this.dbHandler.selectPastDueDate(completedDate) );
    }
    
    void createTable() {
        
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
