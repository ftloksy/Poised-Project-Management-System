import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

public class PersonTable extends JTable {
    DefaultTableModel personModel;
    //TabFrame mainFrame;
    MysqlHandler dbHandler;
    Vector<String> tableHeader ;
    PersonTableSelect personTbSelect ;
    
    PersonTable(TabFrame motherFrame, MysqlHandler dbPosie) {
        super();
        personTbSelect = new PersonTableSelect(motherFrame);
        this.dbHandler = dbPosie ;
        //this.mainFrame = motherFrame ;
        createTableHeader();
        createTable();
    }
    
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
    
    void flashTable () {
        reNewTable ( this.dbHandler.selectPersonRecord() );
    }
    
    void reNewTable( Vector<Vector<String>>  dbRow ) {
        this.personModel.setDataVector( dbRow , this.tableHeader );
    }
    
    void createTable() {
        this.tableHeader = new Vector<String>();
        this.tableHeader.add("id");
        this.tableHeader.add("FirstName");
        this.tableHeader.add("SurName");
        this.tableHeader.add("Telephone");
        this.tableHeader.add("EmailAddress");
        this.tableHeader.add("PhysicalAddress");

        this.personModel = new DefaultTableModel( this.dbHandler.selectPersonRecord() ,this.tableHeader);
        super.setModel(this.personModel);
        super.getSelectionModel().addListSelectionListener(personTbSelect);
    }
}
