import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

public class FinalisedTable extends JTable  {
    DefaultTableModel dbModel;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    FinalisedTableSelect finalisedTbSelect ;
    PmsFrame mainFrame;
    
    FinalisedTable(PmsFrame motherFrame, MysqlHandler dbPosie) {
        //super(motherFrame, dbPosie);
        super();
        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        createTableHeader();
        createTable();
    }

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
    
    // void createTableHeader() {
    //     Vector<String> headerTitle = new Vector<String>();
    //     headerTitle.add("id");
    //     headerTitle.add("FirstName");
    //     headerTitle.add("SurName");
    //     headerTitle.add("Telephone");
    //     headerTitle.add("EmailAddress");
    //     headerTitle.add("PhysicalAddress");
    //     this.tableHeader = headerTitle ;
    // }
    
    void flashTable () {
        reNewTable ( this.dbHandler.selectProjectRecord() );
    }
    
    void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.dbModel.setDataVector( dbRow , this.tableHeader );
    }
    
    void createTable() {
        
        this.finalisedTbSelect = new FinalisedTableSelect(mainFrame);
        
        this.tableHeader = new Vector<String>();
        this.createTableHeader();
        //this.tableHeader.add("id");
        //this.tableHeader.add("FirstName");
        //this.tableHeader.add("SurName");
        //this.tableHeader.add("Telephone");
        //this.tableHeader.add("EmailAddress");
        //this.tableHeader.add("PhysicalAddress");

        this.dbModel = new DefaultTableModel( this.dbHandler.selectProjectRecord() ,this.tableHeader) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            };
        };
        
        super.setModel(this.dbModel);
        super.getSelectionModel().addListSelectionListener(this.finalisedTbSelect);
    }
}
