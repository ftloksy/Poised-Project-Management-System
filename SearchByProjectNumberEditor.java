import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.* ;

public class SearchByProjectNumberEditor extends JPanel {

    Vector<String> personList;

    JLabel projectNoLabel = new JLabel("Project Number", SwingConstants.RIGHT);
    JTextField projectNoText = new JTextField("", 30);
    PmsFrame mainFrame;
    MysqlHandler dbHandler;


    SearchByProjectNumberEditor(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new GridLayout(1, 2, 10, 5)); 

        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie;

        this.resetField();

        super.add(this.projectNoLabel);
        super.add(this.projectNoText);
    }
    
    void resetField() {
        try {
            this.projectNoText.setText(this.dbHandler.nextProjectNumber());
        } catch (SQLException e) {
            this.mainFrame.msgArea.setText("Have database problem.");
        }
    }
}
