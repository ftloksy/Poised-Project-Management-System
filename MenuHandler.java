import javax.swing.*;

public class MenuHandler extends JMenuBar {
    JButton enterItem = new JButton("Enter Person");
    JButton clearItem = new JButton("Clear Person");
    JButton updateItem = new JButton("Update Person");
    JButton deleteItem = new JButton("Delete Person");
    JButton searchItem = new JButton("Search Person");
    JButton listAllItem = new JButton("ListAll Person");
    JButton exitItem = new JButton("Exit");
    //TabFrame mainFrame;
    ListenerExit exitProgram = new ListenerExit();
    ListenerClear clearListener;
    ListenerEnter enterListener;
    ListenerDelete deleteListener;
    ListenerUpdate updateListener;
    ListenerSearch searchListener;
    ListenerListAll listAllListener;
    
    MenuHandler (PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        //this.mainFrame = motherFrame;
        super.add( this.clearItem );
        super.add( this.enterItem );
        super.add( this.updateItem );
        super.add( this.deleteItem );
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
        this.clearListener = new ListenerClear( motherFrame );
        this.enterListener = new ListenerEnter( motherFrame, dbPosie );
        this.deleteListener = new ListenerDelete( motherFrame, dbPosie );
        this.updateListener = new ListenerUpdate( motherFrame, dbPosie );
        this.searchListener = new ListenerSearch( motherFrame, dbPosie );
        this.listAllListener = new ListenerListAll( motherFrame, dbPosie );
        this.clearItem.addActionListener(this.clearListener);
        this.exitItem.addActionListener(this.exitProgram);
        this.enterItem.addActionListener(this.enterListener);
        this.deleteItem.addActionListener(this.deleteListener);
        this.updateItem.addActionListener(this.updateListener);
        this.searchItem.addActionListener(this.searchListener);
        this.listAllItem.addActionListener(this.listAllListener);
    }
}
