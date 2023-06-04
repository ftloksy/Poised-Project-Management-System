package Tab;
import java.awt.*;
import javax.swing.*;

import Handler.MysqlHandler;
import Frame.PmsFrame;
import Listener.TabChangeListener;

/**
 * In this Class have two Class inside.
 * One is PersonTab, this is for handle Person database.
 * One is ProjectTab. this is for handle Project database.
 * TabChangeListener is a Listener, when you click the table,
 * It will trigger TabChangeListener.
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 */
public class PmsTab {
    final static String personTabTilte = "Person";
    final static String projectTabTitle = "Project";
    TabChangeListener changeListener ;
    public ProjectTab projectTab;
    public PersonTab personTab;
    public JTabbedPane tabbedPane ;
    

    /**
     * PmsTab constructor
     * 
     * @param motherFrame    the main Frame ( Root Frame )
     * @param dbPosie        the DatabaseHandler.
     */  
    public PmsTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        this.personTab = new PersonTab(motherFrame, dbPosie);
        this.projectTab = new ProjectTab(motherFrame, dbPosie);
        changeListener = new TabChangeListener(motherFrame);
    }
    
    /**
     * add personTab and projectTab to here.
     */
    public void addComponentToPane(Container pane) {
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab(personTabTilte, personTab);
        tabbedPane.addTab(projectTabTitle, projectTab);
        tabbedPane.setSelectedIndex(0);
        
        tabbedPane.addChangeListener(changeListener);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}
