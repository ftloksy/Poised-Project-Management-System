import javax.swing.*;

/**
 * This will create a Menu at ( Root ) Frame's top side.
 * It has many button. If user click a buttons.
 * It will trigger its relations Listener.
 * 
 * Switch PmsTab, It will have differnet menu button in the Person and Projects tab,
 * It will reset the Menu.
 * 
 * In the Project tab. it have another JTabbedPanel
 * finalised, pastDueDate, searchByProjectNumber and searchByProjectName.
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 * @see         PmsFrame
 */
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

    /** ListenerExist will exist program */
    public ListenerExit exitProgram ;

    /** ListenerClear will clear all textfield in the PersonEditor or ProjectEditor */
    public ListenerClear clearListener;

    /**
     * ListenerEnter will use Editor textfield's values 
     * insert to the Person and Project Database. 
     */
    public ListenerEnter enterListener;

    /**
     * User need click the tables's ( Person or Project ) row.
     * It will update the Editor's Project Number or id Textfield 
     * ( and another Textfield )
     * then user click deleteItem button, then will delete the Person or Project
     * tables in PoisePMS database record follow Project Number or id.
     */
    public ListenerDelete deleteListener;

    /**
     * User need click the tables's ( Person or Project ) row.
     * It will update the Editor's Textfields.
     * then user can update Textfields, 
     * then user click updateItem button, 
     * then will update the Person or Project tables in PoisePMS
     * database record follow Project Number or id.
     */
    public ListenerUpdate updateListener;

    /**
     * User can insert any data in Editor's textfield.
     * It will filter the Person or Project tables
     * User can use this filter to search inside 
     * the Person or Project tables.
     */
    public ListenerSearch searchListener;

    /**
     * After filtering the tables, It will display less rows
     * at tables, User can click listAllItem button,
     * It will reflash the table and display all record in table.
     */
    public ListenerListAll listAllListener;

    /** 
     * User can use finalisedItem buttom to update the record
     * to finalised. user can add the completed data if user 
     * modify the CompletedDate Textfield.
     * If user don't modify the value in completed textfield.
     * program will use "today" ( Currect date ) to set in completed date.
     */
    public ListenerFinalised finalisedListener;

    /**
     * User can use needCompletedItem button to list what
     * Project hasn't completed and need to finish.
     */
    public ListenerNeedCompleted needCompletedListener;

    /**
     * User can modify the completedDate textfield's value
     * And find what project completedDate is less then it.
     * If user hasn't modify the completedDate textfield.
     * It will use "today" ( Currect date ) to find the Project record.
     */
    public ListenerPastDueDate pastDueDateListener;
    
    /**
     * MenuHandler constructor
     * 
     * @param motherFrame the main Frame ( Root Frame )
     * @param dbPosie     the DatabaseHandler.
     */    
    public MenuHandler (PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        this.exitProgram = new ListenerExit(dbPosie) ;
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

    /**
     * In the Project and Person's tab,
     * can reset the Menu to seven button.
     */
    public void reNewMenu(){
        this.redraw();
        super.add( this.clearItem );
        super.add( this.enterItem );
        super.add( this.updateItem );
        super.add( this.deleteItem );
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }

    /** Re-paint the Menu. */
    private void redraw() {
        super.removeAll();
        super.revalidate();
        super.repaint();
    }

    /** Update the Finalised Page's Menu.  */
    public void finalisedMenu() {
        this.redraw();
        super.add( this.finalisedItem );
        super.add( this.needCompletedItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }

    /** Update the Post Due Date Page's Menu.  */
    public void pastDueDateMenu() {
        this.redraw();
        super.add( this.pastDueDateItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    } 

    /** Update the Search By Project Number Page's Menu.  */
    public void searchByProjectNumberMenu() {
        this.redraw();
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }

    /** Update the Search By Project Name Page's Menu.  */
    public void searchByProjectNameMenu() {
        this.redraw();
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
    }
}
