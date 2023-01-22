import java.awt.event.*;
import java.sql.SQLException;

/**
 * This is Listener for [Finalised Project] Button, When User click the Button, 
 * will trigger this action.
 */
public class ListenerFinalised implements ActionListener {
    PmsFrame mainFrame;
    FinalisedEditor finalisedEditor ;
    String completedDate  ;
    String projectNo ;
    MysqlHandler dbHandler;
    
    /**
     * ListenerFinalised constructor
     * 
     * @param motherFrame the main Frame ( Root Frame )
     * @param dbPosie the DatabaseHandler.
     */
    public ListenerFinalised(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie;
    }

    /** 
     * This action will update the Project table's Finalised and insert CompleteDate. 
     * 
     * Listener will Update Project table's Finalised columns value to 1 
     * Project table's Finalised's data Type is boolean so if Project is finalised.
     * set the Finalised to 1.
     * then insert CompleteDate follow completeDate.
     * If the CompletedDate is emtry or not date input format will
     * show message in msgArea at Main Frame.
     */
    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText("");
        this.finalisedEditor = this.mainFrame.pmsTab.projectTab.finalisedEditor;
        this.completedDate = finalisedEditor.completedDateText.getText();
        this.projectNo = finalisedEditor.projectNoText.getText();

        try {
            /* Set the record's Finalised to 1 and update the CompletedDate  */
            this.dbHandler.finalisedProject(this.completedDate, this.projectNo);
            this.mainFrame.msgArea.setText("Finalised The Project.");
            this.mainFrame.pmsTab.projectTab.dbTable.flashTable();
        } catch ( SQLException fe){
            String sqlCode = fe.getSQLState();
            if ( sqlCode.equals("22001") ) {
                this.mainFrame.msgArea.setText( "Please complete the 'Completed Date' field." );
            }
        }
    }
}
