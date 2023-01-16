import java.awt.event.*;

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
        this.finalisedTable = this.mainFrame.pmsTab.projectTab.dbTable ;
        this.finalisedTable.needCompleted();
        this.mainFrame.msgArea.setText("List Need Completed Project.");
    }
}
