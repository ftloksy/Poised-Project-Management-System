import java.awt.*;
import javax.swing.*;

public class FinalisedTab extends JPanel {

    FinalisedEditor dbEditor;
    ProjectTable dbTable;
    JScrollPane dbTableScroll;
    
    MysqlHandler dbHandler;
    
    FinalisedTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());
        this.dbHandler = dbPosie ;
        
        this.dbTable = new ProjectTable ( motherFrame, dbPosie );
        this.dbTableScroll = new JScrollPane( this.dbTable );
        
        this.dbEditor = new FinalisedEditor();

        super.add( this.dbEditor, BorderLayout.NORTH );
        super.add( this.dbTableScroll, BorderLayout.CENTER );
    }

}
