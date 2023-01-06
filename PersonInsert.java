import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector ;

public class PersonInsert extends JPanel {
    
    TabFrame mainFrame;
    JScrollPane dbScroll ;
    PersonTable dbTable ;
    PersonEditor dbEditor ;
    
    PersonInsert(TabFrame motherFrame, MysqlHandler dbPosie) { super();
        
        dbEditor = new PersonEditor();
        super.setLayout(new BorderLayout());
        super.add( this.dbEditor, BorderLayout.NORTH ); 
        this.dbTable = new PersonTable ( motherFrame, dbPosie );
        this.dbScroll = new JScrollPane( this.dbTable );
        super.add( this.dbScroll, BorderLayout.CENTER );
        this.mainFrame = motherFrame;
    }
    
}
