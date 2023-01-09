import java.awt.*;
import javax.swing.*;

public class ProjectEditor extends JPanel {
        //+ " ProjectNumber, ProjectName, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"

    JTextField projectNoText = new JTextField("Project Number ", 30);
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
    
    ProjectEditor() {
        super();
        super.setLayout(new GridLayout(7, 2, 10, 5)); 
        this.projectNoText.setEditable(false);
        super.add(this.projectNoLabel);
        super.add(this.projectNoText); 
        
        super.add(this.projectNameLabel);
        super.add(this.projectNameText);
        
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
 
    }
    
    void resetField() {
        this.projectNoText.setText("project Number");
        this.projectNameText.setText("");
        this.physicalAddressText.setText("");
        this.erfNoText.setText("");
        this.feeChargedText.setText("");
        this.paidTodateText.setText("");
        this.deadlineText.setText("");
    }
}
