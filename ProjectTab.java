import java.awt.*;
import javax.swing.*;
import java.util.ArrayList ;
import java.util.Vector ;
import java.sql.* ;

public class ProjectTab extends JPanel {

    final static int extraWindowWidth = 100;
    
    Vector<String> personList;
    
    DefaultListModel<String> bdgTypeList;
    DefaultListModel<String> pList;
    
    JScrollPane dbScroll ;
    JScrollPane dbScroll2 ;
    JScrollPane dbScroll3 ;
    JScrollPane dbScroll4 ;
    JScrollPane dbScroll5 ;
    JScrollPane dbScroll6 ;

    JList bdgType;
    JList setArchitect;
    JList setContractor;
    JList setCustomer;
    JList setManager;
    JList setEngineer;
    
    ProjectEditor dbEditor;
    ProjectTable dbTable;
    JScrollPane dbTableScroll;
    JPanel gChoice ;
    
    MysqlHandler dbHandler;
    
    ProjectTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());
        //this.assignWeek();
        this.dbHandler = dbPosie ;
        
        this.createBdgList();
        this.createPersonList();

        this.dbScroll = new JScrollPane( this.bdgType);
        this.dbScroll2 = new JScrollPane( this.setArchitect);
        this.dbScroll3 = new JScrollPane( this.setContractor);
        this.dbScroll4 = new JScrollPane( this.setCustomer);
        this.dbScroll5 = new JScrollPane( this.setManager);
        this.dbScroll6 = new JScrollPane( this.setEngineer);

        this.dbScroll.setRowHeaderView(new JLabel("Bdg Type"));
        this.dbScroll2.setRowHeaderView(new JLabel("Architect"));
        this.dbScroll3.setRowHeaderView(new JLabel("Contractor"));
        this.dbScroll4.setRowHeaderView(new JLabel("Customer"));
        this.dbScroll5.setRowHeaderView(new JLabel("Manager"));
        this.dbScroll6.setRowHeaderView(new JLabel("Engineer"));
        
        this.dbEditor = new ProjectEditor();
        
        this.gChoice = new JPanel();
        this.gChoice.setLayout(new GridLayout(3, 2, 10, 5)); 

        this.gChoice.add( this.dbScroll );
        this.gChoice.add( this.dbScroll2 );
        this.gChoice.add( this.dbScroll3 );
        this.gChoice.add( this.dbScroll4 );
        this.gChoice.add( this.dbScroll5 );
        this.gChoice.add( this.dbScroll6 );
        
        this.dbTable = new ProjectTable ( motherFrame, dbPosie );
        this.dbTableScroll = new JScrollPane( this.dbTable );
        
        super.add( this.gChoice, BorderLayout.CENTER );
        super.add( this.dbEditor, BorderLayout.NORTH );
        super.add( this.dbTableScroll, BorderLayout.SOUTH );
    }
    
    void createBdgList() {
        this.bdgTypeList = new DefaultListModel<>();
        this.bdgTypeList.addElement("House");
        this.bdgTypeList.addElement("Apartment");
        this.bdgTypeList.addElement("Block");
        this.bdgTypeList.addElement("Store");
        
        this.bdgType = new JList<>(bdgTypeList);
    }
    
    void createPersonList() {
        
        this.pList = new DefaultListModel<>();
        this.updatePersonList();
        
        //this.bdgType = new JList<String>(bdgTypeList);
        this.setArchitect = new JList<>(this.pList);
        this.setContractor = new JList<>(this.pList);
        this.setCustomer = new JList<>(this.pList);
        this.setManager = new JList<>(this.pList);
        this.setEngineer = new JList<>(this.pList); 
    }
    
    void updatePersonList() {
        try {
            this.personList = this.dbHandler.getPersonList();
        } catch ( SQLException e) {
            e.printStackTrace();
        };
        
        this.pList.removeAllElements();
        for (int i = 0; i < personList.size() ; i ++ ) {
            this.pList.addElement( personList.get(i) );
        }
    }
}
