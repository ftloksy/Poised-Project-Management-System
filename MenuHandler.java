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
    JButton needCompletedItem = new JButton("unCompleted Project");
    JButton pastDueDateItem = new JButton("Past Due Date");

    //TabFrame mainFrame;
    ListenerExit exitProgram = new ListenerExit();
    ListenerClear clearListener;
    ListenerEnter enterListener;
    ListenerDelete deleteListener;
    ListenerUpdate updateListener;
    ListenerSearch searchListener;
    ListenerListAll listAllListener;
    ListenerFinalised finalisedListener;
    ListenerNeedCompleted needCompletedListener;
    ListenerPastDueDate pastDueDateListener;
    
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
        this.needCompletedListener = new ListenerNeedCompleted(motherFrame, dbPosie);
        this.pastDueDateListener = new ListenerPastDueDate(motherFrame, dbPosie);
        this.clearItem.addActionListener(this.clearListener);
        this.exitItem.addActionListener(this.exitProgram);
        this.enterItem.addActionListener(this.enterListener);
        this.deleteItem.addActionListener(this.deleteListener);
        this.updateItem.addActionListener(this.updateListener);
        this.searchItem.addActionListener(this.searchListener);
        this.listAllItem.addActionListener(this.listAllListener);
        this.finalisedItem.addActionListener(this.finalisedListener);
        this.needCompletedItem.addActionListener(this.needCompletedListener);
        this.pastDueDateItem.addActionListener(this.pastDueDateListener);
    }

    void reNewMenu(){
        super.removeAll();
        super.revalidate();
        super.repaint();
        super.add( this.clearItem );
        super.add( this.enterItem );
        super.add( this.updateItem );
        super.add( this.deleteItem );
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }

    void finalisedMenu() {
        super.removeAll();
        super.revalidate();
        super.repaint();
        super.add( this.finalisedItem );
        super.add( this.needCompletedItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }

    void pastDueDateMenu() {
        super.removeAll();
        super.revalidate();
        super.repaint();
        super.add( this.pastDueDateItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    } 

    void searchByProjectNumberMenu() {
        super.removeAll();
        super.revalidate();
        super.repaint();
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }
}
