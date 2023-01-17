import java.awt.event.*;

public class ListenerPastDueDate implements ActionListener {
    PmsFrame mainFrame;
    PastDueDateEditor pastDueDateEditor ;
    ProjectTable finalisedTable ;
    String completedDate  ;
    //String projectNo ;
    MysqlHandler dbHandler;
    
    ListenerPastDueDate(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame;
        this.dbHandler = dbPosie;
    }

    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText("");
        this.pastDueDateEditor = this.mainFrame.pmsTab.projectTab.pastDueDateEditor;
        this.completedDate = pastDueDateEditor.completedDateText.getText();
        //this.projectNo = finalisedEditor.projectNoText.getText();

        //System.out.println("Project No:" + projectNo );

        if ( !this.completedDate.equals("") ) {

            this.finalisedTable = this.mainFrame.pmsTab.projectTab.dbTable ;
            this.finalisedTable.pastDueDate( this.completedDate );
            this.mainFrame.msgArea.setText("Past Due Date Project.");

        } else {

            this.mainFrame.msgArea.setText("Please insert the 'Completed Date' field.");
        }
    }
}
