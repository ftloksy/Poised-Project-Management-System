import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class PastDueDateEditor extends JPanel {

    Vector<String> personList;

    JLabel completedDateLabel = new JLabel("Completed Date", SwingConstants.RIGHT);
    JTextField completedDateText = new JTextField(java.time.LocalDate.now().toString(), 30);


    PastDueDateEditor() {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5)); 

        super.add(this.completedDateLabel);
        super.add(this.completedDateText);
    }
    
    void resetField() {
        this.completedDateText.setText("");
    }
}
