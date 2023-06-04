package Editor;
import java.awt.*;
import javax.swing.*;

import Tab.ProjectTab;

import java.util.Vector;

/** 
 * This a Finalised Editor, It can update the Project table's finalised field.
 * and this is under the ProjectTab's JTabbedPane tabbedPane object.
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         ProjectTab
 */
public class FinalisedEditor extends JPanel {

    Vector<String> personList;

    public JTextField projectNoText = new JTextField("Project Number ", 30);
    JLabel projectNoLabel = new JLabel("Project No", SwingConstants.RIGHT);

    public JTextField projectNameText = new JTextField("Project Name", 30);
    JLabel projectNameLabel = new JLabel("Project Name", SwingConstants.RIGHT);

    public JTextField completedDateText = new JTextField(java.time.LocalDate.now().toString(), 30);
    JLabel completedDateLabel = new JLabel("Completed Date", SwingConstants.RIGHT);

    /** 
     * FinalisedEditor constructor
     * 
     * the Project Number and Project Name don't need modify and Update 
     * So disable edit function. here Project Number and Project Name 
     * is make sure the record is user want to modify,
     * when user update the record, Finalised will update to true.
     * and user can input completed date information.
     */
    public FinalisedEditor() {

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
    
    /** reset the editor JTextFields. */
    public void resetField() {
        this.projectNoText.setText("project Number");
        this.projectNameText.setText("");

        this.completedDateText.setText("");
    }
}
