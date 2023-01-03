import java.awt.*;
import javax.swing.*;

public class PersonInsert extends JPanel {
    JTextField idText = new JTextField("Id", 30);
    JLabel idLabel = new JLabel("Id", SwingConstants.RIGHT);
    PersonInsert() {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5));
        super.add(this.idLabel);
        super.add(this.idText); 
    }
}
