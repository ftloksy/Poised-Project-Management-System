import java.awt.*;
import javax.swing.*;

public class PersonEditor extends JPanel {

    JTextField idText = new JTextField("id", 30);
    JLabel idLabel = new JLabel("Id", SwingConstants.RIGHT);

    JTextField surNameText = new JTextField("SurName", 30);
    JLabel surNameLabel = new JLabel("SurName", SwingConstants.RIGHT);

    JTextField firstNameText = new JTextField("FirstName", 30);
    JLabel firstNameLabel = new JLabel("FirstName", SwingConstants.RIGHT);

    JLabel telephoneLabel = new JLabel("Telephone", SwingConstants.RIGHT);
    JTextField telephoneText = new JTextField("Telephone", 30);

    JLabel emailAddressLabel = new JLabel("EmailAddress", SwingConstants.RIGHT);
    JTextField emalAddressText = new JTextField("EmailAddress", 30);

    JLabel physicalAddressLabel = new JLabel("Address", SwingConstants.RIGHT);
    JTextField physicalAddressText = new JTextField("Address", 30);
    
    PersonEditor() {
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
    
    void resetField() {
        this.idText.setText("Id");
        this.firstNameText.setText("");
        this.surNameText.setText("");
        this.telephoneText.setText("");
        this.emalAddressText.setText("");
        this.physicalAddressText.setText("");
    }
}
