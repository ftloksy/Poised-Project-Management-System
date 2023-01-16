import java.awt.*;
import javax.swing.*;

public class ProjectTab extends JPanel {


    final static String projectTabTitle = "Record Handler";
    final static String finalisedTabTitle = "Finalised Record";
    final static String pastDateTabTitle = "Past Date Record";
    JTabbedPane tabbedPane ;
    PmsFrame mainFrame ;
    MysqlHandler dbHandler ;
    // ProjectEditor projectEditor ;
    FinalisedEditor finalisedEditor;

    // ProjectEditor projectEditor;
    ProjectEditor projectEditor;
    ProjectTable dbTable;
    JScrollPane dbTableScroll;
    ProjectTabChangeListener changeListener ;
    PastDateEditor pastDateEditor ;
    
    ProjectTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());

        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        this.changeListener = new ProjectTabChangeListener(motherFrame);

        this.projectEditor = new ProjectEditor(motherFrame, dbPosie);
        this.finalisedEditor = new FinalisedEditor();
        this.pastDateEditor = new PastDateEditor();

        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab(projectTabTitle, projectEditor);
        tabbedPane.addTab(finalisedTabTitle, finalisedEditor);
        tabbedPane.addTab(pastDateTabTitle, pastDateEditor);
        tabbedPane.setSelectedIndex(0);
        
        tabbedPane.addChangeListener(changeListener);


        // this.pmsProjectTab = new PmsProjectTab(motherFrame, dbPosie);
        // this.pmsProjectTab.addComponentToPane(super.getComponents());
        
        this.dbTable = new ProjectTable ( motherFrame, dbPosie );
        this.dbTableScroll = new JScrollPane( this.dbTable );
        

        // super.add( this.projectEditor, BorderLayout.NORTH );
        super.add( this.tabbedPane, BorderLayout.NORTH);
        super.add( this.dbTableScroll, BorderLayout.CENTER );
    }
}
