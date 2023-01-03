import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ListenerExit implements ActionListener {

    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}
