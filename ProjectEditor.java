import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.* ;

/**
 * This is the Editor, 
 * User can use it to enter record or update record into PoisePMS database Project table.
 * 
 * <table>
 * <tr>
 * <th>JTextField</th>       <th> -+ Project Table Column</th>
 * </tr>
 * <tr>
 * <td>projectNoText</td>    <td> =+ ProjectNumber</td>
 * </tr>
 * <tr>
 * <td>projectNameText</td>  <td> =+ ProjectName</td>
 * </tr>
 * <tr>
 * <td>erfNoText</td>         <td> =+ ERFNumber</td>
 * </tr>
 * <tr>
 * <td>feeChargedText</td>    <td> =+ FeeCharged</td>
 * </tr>
 * <tr>
 * <td>paidTodateText</td>    <td> =+ PaidToDate</td>
 * </tr>
 * <tr>
 * <td>deadlineText</td>      <td> =+ Deadline</td>
 * </tr>
 * <tr>
 * <td>completedDateText</td> <td> =+ CompletedDate</td>
 * </tr>
 * </table>
 *
 * User cannot enter or modify projectNoText JTextField. This information
 * will handle by SQL Server. 
 * completedDateText and setFinalised 
 * just can edit or update in FinalisedEditor.
 * 
 * <table>
 * <tr>
 * <th>JComboBox</th>       <th> -+ Project Table Column</th>
 * </tr>
 * <tr>
 * <td>bdgType</td>         <td> =+ BuildingType</td>
 * </tr>
 * <tr>
 * <td>setArchitect</td>    <td> =+ ArchitectPId</td>
 * </tr>
 * <tr>
 * <td>setContractor</td>   <td> =+ ContractorPId</td>
 * </tr>
 * <tr>
 * <td>setCustomer</td>     <td> =+ CustomerPId</td>
 * </tr>
 * <tr>
 * <td>setManager</td>      <td> =+ ProjectManagerPId</td>
 * </tr>
 * <tr>
 * <td>setEngineer</td>     <td> =+ StructuralEngineerPId</td>
 * </tr>
 * <tr>
 * <td>setFinalised</td>    <td> =+ Finalised</td>
 * </tr>
 * </table>
 * 
 * bdgType is have this items ['House','Apartment','Block','Store'],
 * User can use JComboBox to Select it.
 * setArchitect, setContractor, setCustomer, setEngineer, setManager, 
 * This is ref to the person id. But user cann't 
 * use the ProjectNumber to assign the user. 
 * ( Humen cannot easy use the id number to handle record)
 * User need the SurName and FirstName to handle it, so in the JComboBox has every
 * Person's [ id + FirstName + SurName ] in the row.
 * User can use this to assign<br/>
 * [ Architect, Contractor, Customer, Structural Engineer, Project Manager ]<br/>
 * to a person.
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      MysqlHandler
 */
public class ProjectEditor extends JPanel {

    PmsFrame mainFrame;

    Vector<String> personList;

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

    /**
     * ProjectEditor constructor
     * 
     * @param motherFrame    the main Frame ( Root Frame )
     * @param dbPosie        the DatabaseHandler.
     */   
    public ProjectEditor(PmsFrame motherFrame, MysqlHandler dbPosie) {
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

    /** reset the editor JTextFields. */
    public void resetField() {
        this.projectNoText.setText("Project Number");
        this.projectNameText.setText("");
        this.physicalAddressText.setText("");
        this.erfNoText.setText("");
        this.feeChargedText.setText("");
        this.paidTodateText.setText("");
        this.deadlineText.setText("");
        this.completedDateText.setText("");
    }

    /** Create Building Type JComboBox's content ( Model ) */
    public void createBdgList() {
        this.bdgTypeList = new DefaultComboBoxModel<>();
        this.bdgTypeList.addElement("House");
        this.bdgTypeList.addElement("Apartment");
        this.bdgTypeList.addElement("Block");
        this.bdgTypeList.addElement("Store");
        this.bdgTypeList.addElement("");
        
        this.bdgType = new JComboBox<>(bdgTypeList);
        this.bdgType.setSelectedItem("");;
    }

    /** 
     * Create Finalised JComboBox's content ( Model ),
     * This is ["Yes", "No", ""] choicer. 
     */
    public void createFinalisedList() {
        this.yesNoBoxModel = new DefaultComboBoxModel<>();
        this.yesNoBoxModel.addElement("Yes");
        this.yesNoBoxModel.addElement("No");
        this.yesNoBoxModel.addElement("");

        this.setFinalised = new JComboBox<>(this.yesNoBoxModel);
        this.setFinalised.setSelectedItem("");;
    }
    
    /**
     * Create about Person's JComboBox
     * [ Architect, Contractor, Customer, Structural Engineer, Project Manager ]<br/>
     */
    public void createPersonList() {
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
    
    /** use Person data table's record to update the JComboBox's rows */
    public void updatePersonList() {
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
