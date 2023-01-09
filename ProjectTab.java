import java.awt.*;
import javax.swing.*;
import java.util.ArrayList ;
import java.util.Vector ;
import java.sql.* ;

public class ProjectTab extends JPanel {
    final static String week[] = { "Monday","Tuesday",
        "Wednesday", "Thursday","Friday","Saturday","Sunday", "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh"} ;
    Vector<String> weekList = new Vector<>();
    final static String bdgType[] = { "House", "Apartment", "Block", "Store" };
    final static int extraWindowWidth = 100;
    
    Vector<String> personList;
    
    JScrollPane dbScroll ;
    JScrollPane dbScroll2 ;
    JScrollPane dbScroll3 ;
    JScrollPane dbScroll4 ;
    JScrollPane dbScroll5 ;
    JScrollPane dbScroll6 ;

    JList listLab;
    JList listLab2;
    JList listLab3;
    JList listLab4;
    JList listLab5;
    JList listLab6;
    
    void assignWeek() {
        weekList.add("Monday");
        weekList.add("Tuesday");
        weekList.add("Webnesday");
        weekList.add("Thursday");
        weekList.add("Friday");
        weekList.add("Saturday");
        weekList.add("Sunday");
        weekList.add("aa");
        weekList.add("bb");
        weekList.add("cc");
        weekList.add("dd");
    }

    ProjectEditor projectEditor;
    JPanel gChoice ;
    
    ProjectTab(PmsFrame motherFrame, MysqlHandler dbPosie) {
        super();
        super.setLayout(new BorderLayout());
        this.assignWeek();
        try {
            this.personList = dbPosie.getPersonList();
        } catch ( SQLException e) {
            e.printStackTrace();
        };
        String[] pList = this.personList.toArray(new String[this.personList.size()]);
        
        this.listLab = new JList<String>(pList);
        //this.listLab = new JList<String>(this.weekList.toArray(new String[this.weekList.size()]));
        this.listLab2 = new JList<String>(this.week);
        this.listLab3 = new JList<String>(this.week);
        this.listLab4 = new JList<String>(this.week);
        this.listLab5 = new JList<String>(this.week);
        this.listLab6 = new JList<String>(this.week);

        this.dbScroll = new JScrollPane( this.listLab );
        this.dbScroll2 = new JScrollPane( this.listLab2 );
        this.dbScroll3 = new JScrollPane( this.listLab3 );
        this.dbScroll4 = new JScrollPane( this.listLab4 );
        this.dbScroll5 = new JScrollPane( this.listLab5 );
        this.dbScroll6 = new JScrollPane( this.listLab6 );

        this.dbScroll.setRowHeaderView(new JLabel("Bdg Type"));
        this.dbScroll2.setRowHeaderView(new JLabel("Architect"));
        this.dbScroll3.setRowHeaderView(new JLabel("Contractor"));
        this.dbScroll4.setRowHeaderView(new JLabel("Customer"));
        this.dbScroll5.setRowHeaderView(new JLabel("Manager"));
        this.dbScroll6.setRowHeaderView(new JLabel("Engineer"));
        
        this.projectEditor = new ProjectEditor();
        
        this.gChoice = new JPanel();
        this.gChoice.setLayout(new GridLayout(3, 2, 10, 5)); 

        this.gChoice.add( this.dbScroll );
        this.gChoice.add( this.dbScroll2 );
        this.gChoice.add( this.dbScroll3 );
        this.gChoice.add( this.dbScroll4 );
        this.gChoice.add( this.dbScroll5 );
        this.gChoice.add( this.dbScroll6 );
        
        super.add( this.gChoice, BorderLayout.CENTER);
        super.add( this.projectEditor, BorderLayout.NORTH);
    }
    
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += this.extraWindowWidth;
        return size;
    }
    
}
