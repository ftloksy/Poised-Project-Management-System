import java.awt.*;
import javax.swing.*;

public class ProjectTab extends JPanel {

    ProjectEditor dbEditor;
    ProjectTable dbTable;
    JScrollPane dbTableScroll;
    
    MysqlHandler dbHandler;
    
    ProjectTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());
        this.dbHandler = dbPosie ;
        
        this.dbTable = new ProjectTable ( motherFrame, dbPosie );
        this.dbTableScroll = new JScrollPane( this.dbTable );
        
        this.dbEditor = new ProjectEditor(motherFrame, dbPosie);

        super.add( this.dbEditor, BorderLayout.NORTH );
        super.add( this.dbTableScroll, BorderLayout.CENTER );
    }

}
