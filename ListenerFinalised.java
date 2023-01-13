import java.awt.event.*;

public class ListenerFinalised implements ActionListener {
    PmsFrame mainFrame;
    FinalisedEditor finalisedEditor ;
    String completedDate  ;
    String projectNo ;
    
    ListenerFinalised(PmsFrame motherFrame, MysqlHandler dbPosie){
        this.mainFrame = motherFrame;
        this.finalisedEditor = new FinalisedEditor();
        this.completedDate = finalisedEditor.completedDateText.getText();
        this.projectNo = finalisedEditor.projectNoText.getText();
    }

    public void actionPerformed(ActionEvent e)
    {
        this.mainFrame.msgArea.setText( "Complete Date :" + completedDate + "\nFinalised :" + this.projectNo);
    }
}
