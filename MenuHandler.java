import javax.swing.*;

public class MenuHandler extends JMenuBar {
    JButton enterItem = new JButton("Enter Person");
    JButton clearItem = new JButton("Clear Person");
    JButton updateItem = new JButton("Update Person");
    JButton deleteItem = new JButton("Delete Person");
    JButton searchItem = new JButton("Search Person");
    JButton listAllItem = new JButton("ListAll Person");
    JButton exitItem = new JButton("Exit");
    JButton finalisedItem = new JButton("Finalised Project");
    //TabFrame mainFrame;
    ListenerExit exitProgram = new ListenerExit();
    ListenerClear clearListener;
    ListenerEnter enterListener;
    ListenerDelete deleteListener;
    ListenerUpdate updateListener;
    ListenerSearch searchListener;
    ListenerListAll listAllListener;
    ListenerFinalised finalisedListener;
    
    MenuHandler (PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        //this.mainFrame = motherFrame;
        this.clearListener = new ListenerClear( motherFrame );
        this.enterListener = new ListenerEnter( motherFrame, dbPosie );
        this.deleteListener = new ListenerDelete( motherFrame, dbPosie );
        this.updateListener = new ListenerUpdate( motherFrame, dbPosie );
        this.searchListener = new ListenerSearch( motherFrame, dbPosie );
        this.listAllListener = new ListenerListAll( motherFrame, dbPosie );
        this.finalisedListener = new ListenerFinalised(motherFrame, dbPosie);
        this.clearItem.addActionListener(this.clearListener);
        this.exitItem.addActionListener(this.exitProgram);
        this.enterItem.addActionListener(this.enterListener);
        this.deleteItem.addActionListener(this.deleteListener);
        this.updateItem.addActionListener(this.updateListener);
        this.searchItem.addActionListener(this.searchListener);
        this.listAllItem.addActionListener(this.listAllListener);
        this.finalisedItem.addActionListener(this.finalisedListener);
    }

    void reNewMenu(){
        this.removeAll();
        this.add( this.clearItem );
        this.add( this.enterItem );
        this.add( this.updateItem );
        this.add( this.deleteItem );
        this.add( this.searchItem );
        this.add( this.listAllItem );
        this.add( this.exitItem );
    }

    void finalisedMenu() {
        this.add( this.finalisedItem );
        this.add( this.exitItem );
    }
}
