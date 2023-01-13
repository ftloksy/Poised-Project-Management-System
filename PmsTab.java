import java.awt.*;
import javax.swing.*;

public class PmsTab {
    final static String personTabTilte = "Person";
    final static String projectTabTitle = "Poised";
    final static String finalisedTabTitle = "Finalised";
    TabChangeListener changeListener ;
    ProjectTab projectTab;
    PersonTab personTab;
    FinalisedTab finalisedTab; 
    JTabbedPane tabbedPane ;
    
    PmsTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        this.personTab = new PersonTab(motherFrame, dbPosie);
        this.projectTab = new ProjectTab(motherFrame, dbPosie);
        this.finalisedTab = new FinalisedTab(motherFrame, dbPosie);
        changeListener = new TabChangeListener(motherFrame);
    }
    
    public void addComponentToPane(Container pane) {
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab(personTabTilte, personTab);
        tabbedPane.addTab(projectTabTitle, projectTab);
        tabbedPane.addTab(finalisedTabTitle, finalisedTab);
        tabbedPane.setSelectedIndex(0);
        
        tabbedPane.addChangeListener(changeListener);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}
