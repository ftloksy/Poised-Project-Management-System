import java.awt.*;
import javax.swing.*;
import java.util.Vector ;
import java.sql.* ;

public class ProjectTab extends JPanel {

    final static int extraWindowWidth = 100;
    
    Vector<String> personList;
    
    DefaultComboBoxModel<String> bdgTypeList;

    DefaultComboBoxModel<String> architectBoxModel;
    DefaultComboBoxModel<String> contractorBoxModel;
    DefaultComboBoxModel<String> customerBoxModel;
    DefaultComboBoxModel<String> managerBoxModel;
    DefaultComboBoxModel<String> engineerBoxModel;
    
    JScrollPane dbScroll ;
    JScrollPane dbScroll2 ;
    JScrollPane dbScroll3 ;
    JScrollPane dbScroll4 ;
    JScrollPane dbScroll5 ;
    JScrollPane dbScroll6 ;

    JComboBox<String> bdgType;
    JComboBox<String> setArchitect;
    JComboBox<String> setContractor;
    JComboBox<String> setCustomer;
    JComboBox<String> setManager;
    JComboBox<String> setEngineer;
    
    ProjectEditor dbEditor;
    ProjectTable dbTable;
    JScrollPane dbTableScroll;
    JPanel gChoice ;
    
    MysqlHandler dbHandler;
    
    ProjectTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());
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
        this.bdgTypeList = new DefaultComboBoxModel<>();
        this.bdgTypeList.addElement("House");
        this.bdgTypeList.addElement("Apartment");
        this.bdgTypeList.addElement("Block");
        this.bdgTypeList.addElement("Store");
        this.bdgTypeList.addElement("");
        
        this.bdgType = new JComboBox<>(bdgTypeList);
        this.bdgType.setSelectedItem("");;
    }
    
    void createPersonList() {
        
        this.architectBoxModel = new DefaultComboBoxModel<>();
        this.contractorBoxModel = new DefaultComboBoxModel<>();
        this.customerBoxModel = new DefaultComboBoxModel<>();
        this.managerBoxModel = new DefaultComboBoxModel<>();
        this.engineerBoxModel = new DefaultComboBoxModel<>();

        this.updatePersonList();
        
        this.setArchitect = new JComboBox<>(this.architectBoxModel);
        this.setArchitect.setSelectedItem("");
        this.setContractor = new JComboBox<>(this.contractorBoxModel);
        this.setContractor.setSelectedItem("");
        this.setCustomer = new JComboBox<>(this.customerBoxModel);
        this.setCustomer.setSelectedItem("");
        this.setManager = new JComboBox<>(this.managerBoxModel);
        this.setManager.setSelectedItem("");
        this.setEngineer = new JComboBox<>(this.engineerBoxModel); 
        this.setEngineer.setSelectedItem("");
    }
    
    void updatePersonList() {
        try {
            this.personList = this.dbHandler.getPersonList();
        } catch ( SQLException e) {
            e.printStackTrace();
        };

        this.architectBoxModel.removeAllElements();
        this.contractorBoxModel.removeAllElements();
        this.customerBoxModel.removeAllElements();
        this.managerBoxModel.removeAllElements();
        this.engineerBoxModel.removeAllElements();

        for (int i = 0; i < personList.size() ; i ++ ) {
            this.architectBoxModel.addElement( personList.get(i) );
            this.contractorBoxModel.addElement( personList.get(i) );
            this.customerBoxModel.addElement( personList.get(i) );
            this.managerBoxModel.addElement( personList.get(i) );
            this.engineerBoxModel.addElement( personList.get(i) );
        }

        this.architectBoxModel.addElement("");
        this.contractorBoxModel.addElement("");
        this.customerBoxModel.addElement("");
        this.managerBoxModel.addElement("");
        this.engineerBoxModel.addElement("");
    }
}
