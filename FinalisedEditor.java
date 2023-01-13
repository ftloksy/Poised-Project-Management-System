import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class FinalisedEditor extends JPanel {

    Vector<String> personList;

    JTextField projectNoText = new JTextField("Project Number ", 30);
    JLabel projectNoLabel = new JLabel("Project No", SwingConstants.RIGHT);

    JTextField projectNameText = new JTextField("Project Name", 30);
    JLabel projectNameLabel = new JLabel("Project Name", SwingConstants.RIGHT);

    JLabel completedDateLabel = new JLabel("Completed Date", SwingConstants.RIGHT);
    JTextField completedDateText = new JTextField(java.time.LocalDate.now().toString(), 30);


    FinalisedEditor() {
        super();
        super.setLayout(new GridLayout(3, 2, 10, 5)); 

        this.projectNoText.setEditable(false);
        this.projectNameText.setEditable(false);
        this.completedDateText.setEditable(true);

        super.add(this.projectNoLabel);
        super.add(this.projectNoText); 
        
        super.add(this.projectNameLabel);
        super.add(this.projectNameText);

        super.add(this.completedDateLabel);
        super.add(this.completedDateText);
    }
    
    void resetField() {
        this.projectNoText.setText("project Number");
        this.projectNameText.setText("");

        this.completedDateText.setText("");
    }
}
