import java.awt.event.*;
import java.sql.SQLException;

/*
 * This is Listener for [Finalised Project] Button, When User click the Button, 
 * will trigger this action.
 * It will modify the Project table set the row's Finalised column to 'Yes'.
 * and record the completedDate.
 * If the CompletedDate is emtry or not date input format will
 * show message in msgArea at Main Frame.
 */
public class ListenerFinalised implements ActionListener {
    PmsFrame mainFrame;
    FinalisedEditor finalisedEditor ;
    String completedDate  ;
    String projectNo ;
    MysqlHandler dbHandler;
    
    ListenerFinalised(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie;
    }

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
