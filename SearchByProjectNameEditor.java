import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class SearchByProjectNameEditor extends JPanel {

    Vector<String> personList;

    JLabel projectNameLabel = new JLabel("Project Name", SwingConstants.RIGHT);
    JTextField projectNameText = new JTextField("Project Name", 30);
    PmsFrame mainFrame;
    MysqlHandler dbHandler;


    // SearchByProjectNameEditor(PmsFrame motherFrame, MysqlHandler dbPosie) {
    SearchByProjectNameEditor() {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5)); 

        super.add(this.projectNameLabel);
        super.add(this.projectNameText);
    }
    
    void resetField() {
            this.projectNameText.setText("");
    }
}
