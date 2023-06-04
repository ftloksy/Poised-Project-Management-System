package Tab;
import java.awt.*;
import javax.swing.*;

import Handler.MysqlHandler;
import Editor.FinalisedEditor;
import Editor.PastDueDateEditor;
import Editor.ProjectEditor;
import Editor.SearchByProjectNameEditor;
import Editor.SearchByProjectNumberEditor;
import Frame.PmsFrame;
import Listener.ProjectTabChangeListener;
import Table.ProjectTable;

/**
 * In this Class have five Class (pages ) inside.
 * ( Record Handler ) User can use this page to enter, update, search, delete record in Project data table.
 * ( Finalised Record ) User can use this page to update the Project record to finalised,
 * user need to set the completed data, default is current data ( Today ).
 * ( Past Data Record ) User can set the completedDate JTextField, the Program will find Project table,
 * What record's Deadline is early the completedDate and finalised is false.
 * ( Search by Project Number ) Search Project's record by the Project Number.
 * ( Search by Project Name ) Search Project's record by the Project Name.
 * 
 * And The Project JTable is at the button.
 * 
 * @author   Frankie Chow
 * @version  2023-1-23
 * @see      ProjectTable
 * @see      ProjectEditor
 */
public class ProjectTab extends JPanel {

    final static String projectTabTitle = "Record Handler";
    final static String finalisedTabTitle = "Finalised Record";
    final static String pastDateTabTitle = "Past Due Date Record";
    final static String searchByProjectNumberTabTitle = "Search by Project Number";
    final static String searchByProjectNameTabTitle = "Search by Project Name";
    public JTabbedPane tabbedPane ;
    PmsFrame mainFrame ;
    MysqlHandler dbHandler ;
    public FinalisedEditor finalisedEditor;

    public ProjectEditor projectEditor;
    public ProjectTable dbTable;
    JScrollPane dbTableScroll;
    ProjectTabChangeListener changeListener ;
    public PastDueDateEditor pastDueDateEditor ;
    public SearchByProjectNumberEditor searchByProjectNumberEditor ;
    public SearchByProjectNameEditor searchByProjectNameEditor;
    
    /**
     * ProjectTab constructor
     * 
     * @param motherFrame   the main Frame ( Root Frame )
     * @param dbPosie       the DatabaseHandler.
     */   
    public ProjectTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());

        this.dbHandler = dbPosie ;
        this.mainFrame = motherFrame ;
        this.changeListener = new ProjectTabChangeListener(motherFrame);

        this.projectEditor = new ProjectEditor(motherFrame, dbPosie);
        this.finalisedEditor = new FinalisedEditor();
        this.pastDueDateEditor = new PastDueDateEditor();
        this.searchByProjectNumberEditor = new SearchByProjectNumberEditor(motherFrame, dbPosie);
        this.searchByProjectNameEditor = new SearchByProjectNameEditor();
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab(projectTabTitle, projectEditor);
        tabbedPane.addTab(finalisedTabTitle, finalisedEditor);
        tabbedPane.addTab(pastDateTabTitle, pastDueDateEditor);
        tabbedPane.addTab(searchByProjectNumberTabTitle, searchByProjectNumberEditor);
        tabbedPane.addTab(searchByProjectNameTabTitle, searchByProjectNameEditor);
        tabbedPane.setSelectedIndex(0);
        
        tabbedPane.addChangeListener(changeListener);

        this.dbTable = new ProjectTable ( motherFrame, dbPosie );
        this.dbTableScroll = new JScrollPane( this.dbTable );
        
        super.add( this.tabbedPane, BorderLayout.NORTH);
        super.add( this.dbTableScroll, BorderLayout.CENTER );
    }

    /** Re-paint this tabbedPane */
    public void refleshView() {
        super.revalidate();
        super.repaint();
    }
}
