package Editor;
import java.awt.*;
import javax.swing.*;

import Handler.MysqlHandler;
import Frame.PmsFrame;
import Tab.ProjectTab;
import Table.ProjectTable;

import java.util.Vector;

/** 
 * This a SearchByProjectName page Editor, It can search Project data table by Project Name.
 * and this is under the ProjectTab's JTabbedPane tabbedPane object.
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      ProjectTable
 * @see      ProjectTab
 */
public class SearchByProjectNameEditor extends JPanel {

    Vector<String> personList;

    JLabel projectNameLabel = new JLabel("Project Name", SwingConstants.RIGHT);
    public JTextField projectNameText = new JTextField("Project Name", 30);
    PmsFrame mainFrame;
    MysqlHandler dbHandler;

    /** SearchByProjectNameEditor constructor */
    public SearchByProjectNameEditor() {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5)); 

        super.add(this.projectNameLabel);
        super.add(this.projectNameText);
    }
    
    /** Re-set the JTextField.  */
    public void resetField() {
            this.projectNameText.setText("");
    }
}
