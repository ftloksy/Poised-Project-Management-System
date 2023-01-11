import java.awt.*;
import javax.swing.*;

public class PersonTab extends JPanel {
    
    PmsFrame mainFrame;
    JScrollPane dbScroll ;
    PersonTable dbTable ;
    PersonEditor dbEditor ;
    
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
