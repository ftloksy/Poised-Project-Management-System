import java.awt.event.*;
import java.sql.SQLException;

/*
 * This is Listener for [ Past Due Date ] Button in "Past Due Date Record" page at ProjectTab, 
 * When User click the Button, that will trigger this action.
 */
public class ListenerPastDueDate implements ActionListener {
    PmsFrame mainFrame;
    PastDueDateEditor pastDueDateEditor ;
    ProjectTable projectTable ;
    String targetDate  ;
    MysqlHandler dbHandler;
    
    ListenerPastDueDate(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie;
    }

    public void actionPerformed(ActionEvent e)
    {
        /* Get the targetDateText date from pastDueDateEditor */
        this.mainFrame.msgArea.setText("");
        this.pastDueDateEditor = this.mainFrame.pmsTab.projectTab.pastDueDateEditor;
        this.targetDate = pastDueDateEditor.targetDateText.getText();

        if ( !this.targetDate.equals("") ) {

            this.projectTable = this.mainFrame.pmsTab.projectTab.dbTable ;
            /* 
             * Find the record and update 
             * The record's Deadline is early than targetDate 
             * and Finalised is '0' ( This Project isn't Finalised ) .
             * the ProjectTable JTable.
             */
            try {
                this.projectTable.pastDueDate( this.targetDate );
                this.mainFrame.msgArea.setText("Past Due Date Project.");
            } catch ( SQLException pdd ) {
                this.mainFrame.msgArea.setText( pdd.getMessage() );
            }

        } else {

            this.mainFrame.msgArea.setText("Please insert the 'Completed Date' field.");
        }
    }
}
