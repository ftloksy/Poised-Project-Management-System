import java.awt.*;
import javax.swing.*;

public class MenuHandler extends JMenuBar {
    JButton enterItem = new JButton("Enter book");
    JButton clearItem = new JButton("Clear field");
    JButton updateItem = new JButton("Update book");
    JButton deleteItem = new JButton("Delete book");
    JButton searchItem = new JButton("Search book");
    JButton listAllItem = new JButton("ListAll book");
    JButton exitItem = new JButton("Exit");
    TabFrame mainFrame;
    
    MenuHandler (TabFrame motherFrame) {
        super();
        super.add( this.clearItem );
        super.add( this.enterItem );
        super.add( this.updateItem );
        super.add( this.deleteItem );
        super.add( this.searchItem );
        super.add( this.listAllItem );
        super.add( this.exitItem );
        this.mainFrame = motherFrame;
    }
}
