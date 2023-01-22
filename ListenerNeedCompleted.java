import java.awt.event.*;
import java.sql.* ;

/*
 * This is Listener for [ Need Complete Date ] Button, 
 * in "Finalised Record" page at ProjectTab, 
 * When User click the Button, it will trigger this action.
 */
public class ListenerNeedCompleted implements ActionListener {
    PmsFrame mainFrame;
    MysqlHandler dbHandler;

    ProjectTable finalisedTable;

    /**
     * ListenerNeedCompleted constructor
     * 
     * @param motherFrame the main Frame ( Root Frame )
     * @param dbPosie the DatabaseHandler.
     */   
    public ListenerNeedCompleted(PmsFrame motherFrame, MysqlHandler dbPosie) {
        this.dbHandler = dbPosie;
        this.mainFrame = motherFrame;
    }

    /** 
     * Find the Project Record. that data column Finalised is 0. 
     * The project is not finalised.
     */
    public void actionPerformed(ActionEvent e)
    {

        this.finalisedTable = this.mainFrame.pmsTab.projectTab.dbTable ;
        try {
            this.finalisedTable.needCompleted();
            this.mainFrame.msgArea.setText("List Need Completed Project.");
        } catch ( SQLException nc ) {
            this.mainFrame.msgArea.setText( nc.getMessage() );
        }
    }
}
