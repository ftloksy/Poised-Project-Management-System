import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

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
        this.tableHeader = headerTitle ;
    }
    
    void flashTable () {
        reNewTable ( this.dbHandler.selectProjectRecord() );
    }
    
    void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.dbModel.setDataVector( dbRow , this.tableHeader );
    }
    
    void createTable() {
        
        this.projectTbSelect = new ProjectTableSelect(this.mainFrame);
        
        this.tableHeader = new Vector<String>();
        this.createTableHeader();

        this.dbModel = new DefaultTableModel( this.dbHandler.selectProjectRecord() ,this.tableHeader) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            };
        };
        
        super.setModel(this.dbModel);
        super.getSelectionModel().addListSelectionListener(this.projectTbSelect);
    }
}
