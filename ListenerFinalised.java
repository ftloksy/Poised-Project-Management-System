import java.awt.event.*;
import java.sql.SQLException;

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
        this.finalisedEditor = this.mainFrame.pmsTab.finalisedTab.dbEditor;
        this.completedDate = finalisedEditor.completedDateText.getText();
        this.projectNo = finalisedEditor.projectNoText.getText();

        //System.out.println("Project No:" + projectNo );

        if ( !this.completedDate.equals("") ) {

            try {
                this.dbHandler.finalisedProject(this.completedDate, this.projectNo);
                this.mainFrame.msgArea.setText("Finalised The Project.");
                this.mainFrame.pmsTab.finalisedTab.dbTable.flashTable();
            } catch ( SQLException fe){
                this.mainFrame.msgArea.setText( fe.getMessage() );
            }

        } else {

            this.mainFrame.msgArea.setText("Please insert the 'Completed Date' field.");
        }
    }
}
