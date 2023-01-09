import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

public class ProjectTable extends JTable {
    DefaultTableModel personModel;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    //PersonTableSelect personTbSelect ;
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
        this.personModel.setDataVector( dbRow , this.tableHeader );
    }
    
    void createTable() {
        
        //this.personTbSelect = new PersonTableSelect(this.mainFrame);
        
        this.tableHeader = new Vector<String>();
        this.createTableHeader();

        this.personModel = new DefaultTableModel( this.dbHandler.selectProjectRecord() ,this.tableHeader) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            };
        };
        
        super.setModel(this.personModel);
        //super.getSelectionModel().addListSelectionListener(this.personTbSelect);
    }
}
