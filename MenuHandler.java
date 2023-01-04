import java.awt.*;
import javax.swing.*;

public class MenuHandler extends JMenuBar {
    JButton enterItem = new JButton("Enter Person");
    JButton clearItem = new JButton("Clear Person");
    JButton updateItem = new JButton("Update Person");
    JButton deleteItem = new JButton("Delete Person");
    JButton searchItem = new JButton("Search Person");
    JButton listAllItem = new JButton("ListAll Person");
    JButton exitItem = new JButton("Exit");
    TabFrame mainFrame;
    ListenerExit exitProgram = new ListenerExit();
    ListenerLab labListener;
    ListenerEnter enterListener;
    
    MenuHandler (TabFrame motherFrame, MysqlHandler dbPosie) {
        super();
        this.mainFrame = motherFrame;
        super.add( this.clearItem );
        super.add( this.enterItem );
        super.add( this.updateItem );
        super.add( this.deleteItem );
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
        labListener = new ListenerLab( this.mainFrame );
        enterListener = new ListenerEnter( this.mainFrame, dbPosie );
        this.clearItem.addActionListener(labListener);
        this.exitItem.addActionListener(exitProgram);
        this.enterItem.addActionListener(enterListener);
    }
}
