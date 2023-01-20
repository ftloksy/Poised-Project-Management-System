import java.awt.event.*;

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
        this.mainFrame.msgArea.setText("");
        this.pastDueDateEditor = this.mainFrame.pmsTab.projectTab.pastDueDateEditor;
        this.targetDate = pastDueDateEditor.targetDateText.getText();

        if ( !this.targetDate.equals("") ) {

            this.projectTable = this.mainFrame.pmsTab.projectTab.dbTable ;
            this.projectTable.pastDueDate( this.targetDate );
            this.mainFrame.msgArea.setText("Past Due Date Project.");

        } else {

            this.mainFrame.msgArea.setText("Please insert the 'Completed Date' field.");
        }
    }
}
