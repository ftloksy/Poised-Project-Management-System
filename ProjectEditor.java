import java.awt.*;
import javax.swing.*;

public class ProjectEditor extends JPanel {

    JTextField projectNoText = new JTextField("Project Number ", 30);
    JLabel projectNoLabel = new JLabel("Project No", SwingConstants.RIGHT);

    JTextField erfNoText = new JTextField("ERF Number", 30);
    JLabel erfNoLabel = new JLabel("ERF No", SwingConstants.RIGHT);

    JTextField projectNameText = new JTextField("Project Name", 30);
    JLabel projectNameLabel = new JLabel("Project Name", SwingConstants.RIGHT);

    JTextField deadlineText= new JTextField("Deadline Date", 30);
    JLabel deadlineLabel = new JLabel("Deadline", SwingConstants.RIGHT);

    JLabel feeChargedLabel = new JLabel("Fee Charged", SwingConstants.RIGHT);
    JTextField feeChargedText = new JTextField("Fee Charged", 30);

    JLabel paidTodayLabel = new JLabel("Paid today", SwingConstants.RIGHT);
    JTextField paidTodayText = new JTextField("Paid today", 30);
    
    ProjectEditor() {
        super();
        super.setLayout(new GridLayout(6, 2, 10, 5)); 
        this.projectNoText.setEditable(false);
        super.add(this.projectNoLabel);
        super.add(this.projectNoText); 

        super.add(this.erfNoLabel);
        super.add(this.erfNoText); 

        super.add(this.projectNameLabel);
        super.add(this.projectNameText); 

        super.add(this.deadlineLabel);
        super.add(this.deadlineText); 

        super.add(this.feeChargedLabel);
        super.add(this.feeChargedText); 

        super.add(this.paidTodayLabel);
        super.add(this.paidTodayText); 
    }
    
    void resetField() {
        this.projectNoText.setText("project Number");
        this.projectNameText.setText("");
        this.erfNoText.setText("");
        this.deadlineText.setText("");
        this.feeChargedText.setText("");
        this.paidTodayText.setText("");
    }
}
