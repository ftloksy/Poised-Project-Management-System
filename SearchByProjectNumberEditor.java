import java.awt.*;
import javax.swing.*;
import java.util.Vector;

/** 
 * This a SearchByProjectNumber page Editor, It can search Project data table by Project Number.
 * and this is under the ProjectTab's JTabbedPane tabbedPane object.
 */
public class SearchByProjectNumberEditor extends JPanel {

    Vector<String> personList;

    JLabel projectNoLabel = new JLabel("Project Number", SwingConstants.RIGHT);
    JTextField projectNoText = new JTextField("", 30);
    PmsFrame mainFrame;
    MysqlHandler dbHandler;

    /** SearchByProjectNameEditor constructor */
    public SearchByProjectNumberEditor(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5)); 

        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie;

        this.resetField();

        super.add(this.projectNoLabel);
        super.add(this.projectNoText);
    }

    /** Re-set the JTextField.  */
    public void resetField() {
        this.projectNoText.setText("0");
    }
}
