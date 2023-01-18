import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.* ;

public class ProjectEditor extends JPanel {

    PmsFrame mainFrame;

    Vector<String> personList;

        //+ " ProjectNumber, ProjectName, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"

    JTextField projectNoText = new JTextField("Project Number", 30);
    JLabel projectNoLabel = new JLabel("Project No", SwingConstants.RIGHT);

    JTextField projectNameText = new JTextField("Project Name", 30);
    JLabel projectNameLabel = new JLabel("Project Name", SwingConstants.RIGHT);

    JTextField physicalAddressText = new JTextField("Physical Address", 30);
    JLabel physicalAddressLabel = new JLabel("Physical Address", SwingConstants.RIGHT);

    JTextField erfNoText = new JTextField("ERF Number", 30);
    JLabel erfNoLabel = new JLabel("ERF No", SwingConstants.RIGHT);

    JLabel feeChargedLabel = new JLabel("Fee Charged", SwingConstants.RIGHT);
    JTextField feeChargedText = new JTextField("Fee Charged", 30);

    JLabel paidTodateLabel = new JLabel("Paid to date", SwingConstants.RIGHT);
    JTextField paidTodateText = new JTextField("Paid to date", 30);

    JTextField deadlineText= new JTextField("Deadline Date", 30);
    JLabel deadlineLabel = new JLabel("Deadline", SwingConstants.RIGHT);

    JLabel bdgTypeLabel = new JLabel("Building Type", SwingConstants.RIGHT);
    JLabel architectLabel = new JLabel("Architect", SwingConstants.RIGHT);
    JLabel contractorLabel = new JLabel("Contractor", SwingConstants.RIGHT);
    JLabel customerLabel = new JLabel("Customer", SwingConstants.RIGHT);
    JLabel managerLabel = new JLabel("Manager", SwingConstants.RIGHT);
    JLabel engineerLabel = new JLabel("Engineer", SwingConstants.RIGHT);
    JLabel finalisedLabel = new JLabel("Finalised", SwingConstants.RIGHT);

    JLabel completedDateLabel = new JLabel("Completed Date", SwingConstants.RIGHT);
    JTextField completedDateText = new JTextField("", 30);

    DefaultComboBoxModel<String> bdgTypeList;

    DefaultComboBoxModel<String> architectBoxModel;
    DefaultComboBoxModel<String> contractorBoxModel;
    DefaultComboBoxModel<String> customerBoxModel;
    DefaultComboBoxModel<String> managerBoxModel;
    DefaultComboBoxModel<String> engineerBoxModel;

    DefaultComboBoxModel<String> yesNoBoxModel;

    JComboBox<String> bdgType;
    JComboBox<String> setArchitect;
    JComboBox<String> setContractor;
    JComboBox<String> setCustomer;
    JComboBox<String> setManager;
    JComboBox<String> setEngineer;

    JComboBox<String> setFinalised;

    MysqlHandler dbHandler;

    ProjectEditor(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new GridLayout(15, 2, 10, 5)); 

        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie ;
        this.createBdgList();
        this.createFinalisedList();
        this.projectNoText.setEditable(false);
        this.setFinalised.setEnabled(false);
        this.completedDateText.setEditable(false);
        this.createPersonList();

        super.add(this.projectNoLabel);
        super.add(this.projectNoText); 
        
        super.add(this.projectNameLabel);
        super.add(this.projectNameText);

        super.add(this.bdgTypeLabel);
        super.add(this.bdgType);
        
        super.add(this.physicalAddressLabel);
        super.add(this.physicalAddressText);
        
        super.add(this.erfNoLabel);
        super.add(this.erfNoText); 

        super.add(this.feeChargedLabel);
        super.add(this.feeChargedText); 

        super.add(this.paidTodateLabel);
        super.add(this.paidTodateText);

        super.add(this.deadlineLabel);
        super.add(this.deadlineText);

        /*    */

        super.add(this.architectLabel);
        super.add(this.setArchitect);

        super.add(this.contractorLabel);
        super.add(this.setContractor);

        super.add(this.customerLabel);
        super.add(this.setCustomer);

        super.add(this.managerLabel);
        super.add(this.setManager);

        super.add(this.engineerLabel);
        super.add(this.setEngineer);

        super.add(this.finalisedLabel);
        super.add(this.setFinalised);

        super.add(this.completedDateLabel);
        super.add(this.completedDateText);

    }

    void resetField() {
        this.projectNoText.setText("Project Number");
        this.projectNameText.setText("");
        this.physicalAddressText.setText("");
        this.erfNoText.setText("");
        this.feeChargedText.setText("");
        this.paidTodateText.setText("");
        this.deadlineText.setText("");
        this.completedDateText.setText("");
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

    void createFinalisedList() {
        this.yesNoBoxModel = new DefaultComboBoxModel<>();
        this.yesNoBoxModel.addElement("Yes");
        this.yesNoBoxModel.addElement("No");
        this.yesNoBoxModel.addElement("");

        this.setFinalised = new JComboBox<>(this.yesNoBoxModel);
        this.setFinalised.setSelectedItem("");;
    }
    
    void createPersonList() {
        this.architectBoxModel = new DefaultComboBoxModel<>();
        this.contractorBoxModel = new DefaultComboBoxModel<>();
        this.customerBoxModel = new DefaultComboBoxModel<>();
        this.managerBoxModel = new DefaultComboBoxModel<>();
        this.engineerBoxModel = new DefaultComboBoxModel<>();
        
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

        this.updatePersonList();
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

        this.bdgType.setSelectedItem("");
        this.setFinalised.setSelectedItem("");

        this.setArchitect.setSelectedItem("");
        this.setContractor.setSelectedItem("");
        this.setCustomer.setSelectedItem("");
        this.setManager.setSelectedItem("");
        this.setEngineer.setSelectedItem("");
    }

}
