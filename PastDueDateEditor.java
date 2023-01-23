import java.awt.*;
import javax.swing.*;
import java.util.Vector;

/** 
 * This a Past Due Editor, It can search the Project table's record follow 
 * Target date, this Project tables entry's Deadline is early than Target Date 
 * and the Finalised is not finalised.
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         ProjectEditor 
 * @see         MysqlHandler
 */
public class PastDueDateEditor extends JPanel {

    Vector<String> personList;

    JLabel targetDateLabel = new JLabel("Target Date", SwingConstants.RIGHT);
    /* Set the targetDateText is current day ( today ) */
    JTextField targetDateText = new JTextField(java.time.LocalDate.now().toString(), 30);

    /** PastDueDateEditor constructor */
    public PastDueDateEditor() {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5)); 

        super.add(this.targetDateLabel);
        super.add(this.targetDateText);
    }
    
    /** Re-set the JTextField.  */
    public void resetField() {
        this.targetDateText.setText("");
    }
}
