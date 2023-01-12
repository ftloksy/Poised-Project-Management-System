import java.awt.*;
import javax.swing.*;

public class PmsTab {
    final static String PersonTabTilte = "Person";
    final static String ProjectTabTitle = "Tab with JButtons";
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

        tabbedPane.addTab(PersonTabTilte, personTab);
        tabbedPane.addTab(ProjectTabTitle, projectTab);
        tabbedPane.setSelectedIndex(0);
        
        tabbedPane.addChangeListener(changeListener);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}
