import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

public class PersonInsert extends JPanel {
    TabFrame mainFrame;
    JTextField surNameText = new JTextField("SurName", 30);
    JLabel surNameLabel = new JLabel("SurName", SwingConstants.RIGHT);

    JTextField firstNameText = new JTextField("FirstName", 30);
    JLabel firstNameLabel = new JLabel("FirstName", SwingConstants.RIGHT);

    JLabel telephoneLabel = new JLabel("Telephone", SwingConstants.RIGHT);
    JTextField telephoneText = new JTextField("Telephone", 30);

    JLabel emailAddressLabel = new JLabel("EmailAddress", SwingConstants.RIGHT);
    JTextField emalAddressText = new JTextField("EmailAddress", 30);

    JLabel physicalAddressLabel = new JLabel("Address", SwingConstants.RIGHT);
    JTextField physicalAddressText = new JTextField("Address", 30);
        
    JPanel dbEditor;
    JScrollPane dbScroll ;
    Vector<String> tableHeader ;
    DefaultTableModel personModel;
    PersonTable dbTable ;
    
    PersonInsert(TabFrame motherFrame, MysqlHandler dbPosie) { super();
        this.dbEditor = new JPanel();
        this.dbEditor.setLayout(new GridLayout(5, 2, 10, 5)); 

        this.dbEditor.add(this.surNameLabel);
        this.dbEditor.add(this.surNameText); 

        this.dbEditor.add(this.firstNameLabel);
        this.dbEditor.add(this.firstNameText); 

        this.dbEditor.add(this.telephoneLabel);
        this.dbEditor.add(this.telephoneText); 

        this.dbEditor.add(this.emailAddressLabel);
        this.dbEditor.add(this.emalAddressText); 

        this.dbEditor.add(this.physicalAddressLabel);
        this.dbEditor.add(this.physicalAddressText); 
        
        super.setLayout(new BorderLayout());
        super.add( this.dbEditor, BorderLayout.NORTH ); 
        
        this.dbTable = new PersonTable ( motherFrame, dbPosie );
        this.dbScroll = new JScrollPane( this.dbTable );
        
        super.add( this.dbScroll, BorderLayout.CENTER );
        this.mainFrame = motherFrame;
        
    }
    
}
