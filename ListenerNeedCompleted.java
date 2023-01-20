import java.awt.event.*;

/*
 * This is Listener for [ Need Complete Date ] Button, 
 *  in "Finalised Record" page at ProjectTab, 
 * When User click the Button, it will trigger this action.
 */
public class ListenerNeedCompleted implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;

    ProjectTable finalisedTable;

    ListenerNeedCompleted(PmsFrame motherFrame, MysqlHandler dbPosie) {
        this.dbHandler = dbPosie;
        this.mainFrame = motherFrame;
    }

    public void actionPerformed(ActionEvent e)
    {
        /* 
         * Find the Project Record. that record Finalised is 0. 
         * The project is not finalised.
         */
        this.finalisedTable = this.mainFrame.pmsTab.projectTab.dbTable ;
        this.finalisedTable.needCompleted();
        this.mainFrame.msgArea.setText("List Need Completed Project.");
    }
}
