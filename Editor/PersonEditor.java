package Editor;
import java.awt.*;
import javax.swing.*;

/**
 * This is editor for PoisedPMS database Person Table.
 * All User's information is use this editor to Enter and Modify
 * Include Architect, Contractor, Customer, Project Manager, Structural
 * Engineer.
 * 
 * <table>
 * <tr>
 * <th>JTextField</th>
 * <th>-+ Person Table column</th>
 * </tr>
 * <tr>
 * <td>idText</td>
 * <td>=+ id</td>
 * </tr>
 * <tr>
 * <td>surNameText</td>
 * <td>=+ SurName</td>
 * </tr>
 * <tr>
 * <td>firstNameText</td>
 * <td>=+ FirstName</td>
 * </tr>
 * <tr>
 * <td>telephoneText</td>
 * <td>=+ Telephone</td>
 * </tr>
 * <tr>
 * <td>emailAddressText</td>
 * <td>=+ EmailAddress</td>
 * </tr>
 * <tr>
 * <td>physicalAddressText</td>
 * <td>=+ PhysicalAddress</td>
 * </tr>
 * </table>
 * 
 * user cann't enter or modify idText.
 * When Record entry, SQL server will care this field.
 * When Search and Modify the Record.
 * PersonTableSelect ( Listener ) will care this.
 * 
 * @author    Frankie Chow
 * @version   2023-1-23
 */
public class PersonEditor extends JPanel {

    public JTextField idText = new JTextField("id", 30);
    JLabel idLabel = new JLabel("Id", SwingConstants.RIGHT);

    public JTextField surNameText = new JTextField("SurName", 30);
    JLabel surNameLabel = new JLabel("SurName", SwingConstants.RIGHT);

    public JTextField firstNameText = new JTextField("FirstName", 30);
    JLabel firstNameLabel = new JLabel("FirstName", SwingConstants.RIGHT);

    public JLabel telephoneLabel = new JLabel("Telephone", SwingConstants.RIGHT);
    public JTextField telephoneText = new JTextField("Telephone", 30);

    public JLabel emailAddressLabel = new JLabel("EmailAddress", SwingConstants.RIGHT);
    public JTextField emalAddressText = new JTextField("EmailAddress", 30);

    public JLabel physicalAddressLabel = new JLabel("Address", SwingConstants.RIGHT);
    public JTextField physicalAddressText = new JTextField("Address", 30);

    /** PastDueDateEditor constructor */
    public PersonEditor() {
        super();
        super.setLayout(new GridLayout(6, 2, 10, 5));
        this.idText.setEditable(false);
        super.add(this.idLabel);
        super.add(this.idText);

        super.add(this.surNameLabel);
        super.add(this.surNameText);

        super.add(this.firstNameLabel);
        super.add(this.firstNameText);

        super.add(this.telephoneLabel);
        super.add(this.telephoneText);

        super.add(this.emailAddressLabel);
        super.add(this.emalAddressText);

        super.add(this.physicalAddressLabel);
        super.add(this.physicalAddressText);
    }

    /**
     * When user want to search or enter new record.
     * User maybe need to reset all JTextField in PersonEditor.
     */
    public void resetField() {
        this.idText.setText("Id");
        this.firstNameText.setText("");
        this.surNameText.setText("");
        this.telephoneText.setText("");
        this.emalAddressText.setText("");
        this.physicalAddressText.setText("");
    }
}
