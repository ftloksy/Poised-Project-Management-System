import java.awt.*;
import javax.swing.*;

/**
 * This Tab has two parts,
 * PersonEditor in on the top, it is for input data about the Person table insert.
 * PersonTable is on the buttom, it is for display Person table rows.
 * 
 * @author     Frankie Chow
 * @version    2023-1-23
 * @see        PersonTable
 * @see        PersonEditor
 */
public class PersonTab extends JPanel {
    
    PmsFrame mainFrame;
    JScrollPane dbScroll ;
    PersonTable dbTable ;
    PersonEditor dbEditor ;
    
    /**
     * PersonTab constructor
     * 
     * @param motherFrame   the main Frame ( Root Frame )
     * @param dbPosie       the DatabaseHandler.
     */ 
    PersonTab(PmsFrame motherFrame, MysqlHandler dbPosie) { super();
        
        dbEditor = new PersonEditor();
        super.setLayout(new BorderLayout());
        super.add( this.dbEditor, BorderLayout.NORTH ); 
        this.dbTable = new PersonTable ( motherFrame, dbPosie );
        this.dbScroll = new JScrollPane( this.dbTable );
        super.add( this.dbScroll, BorderLayout.CENTER );
        this.mainFrame = motherFrame;
    }
}
