import java.awt.*;
import javax.swing.*;

/*
 * In this Class have two subclass.
 * One is PersonTab, this is for handle Person database.
 * One is ProjectTab. this is for handle Project database.
 * TabChangeListener is a Listener, when you click the table,
 * It will trigger TabChangeListener.
 */
public class PmsTab {
    final static String personTabTilte = "Person";
    final static String projectTabTitle = "Project";
    TabChangeListener changeListener ;
    ProjectTab projectTab;
    PersonTab personTab;
    JTabbedPane tabbedPane ;
    
    PmsTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        this.personTab = new PersonTab(motherFrame, dbPosie);
        this.projectTab = new ProjectTab(motherFrame, dbPosie);
        changeListener = new TabChangeListener(motherFrame);
    }
    
    public void addComponentToPane(Container pane) {
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab(personTabTilte, personTab);
        tabbedPane.addTab(projectTabTitle, projectTab);
        tabbedPane.setSelectedIndex(0);
        
        tabbedPane.addChangeListener(changeListener);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}
