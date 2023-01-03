import java.sql.*;
import java.util.Vector;

/*
 * This class is handle mysql require.
 * Include create table, Insert, delete, update and select.
 */
public class MysqlHandler {
    Connection connection = null ;
    Statement statement = null ;

    /* Create table */
    /* I think need first create a customer in Person then can create the Poised */
    String createProjectTableSQL = "CREATE TABLE IF NOT EXISTS Project ("
        + " PersonRole ENUM('Architect',"
        + " 'Contractor', 'Customer', 'ProjectManager',"
        + " 'StructuralEngineer') NOT NULL,"
        + " ProjectNumber int(6) ZEROFILL NOT NULL,"
        + " PersonId int(6) UNSIGNED, "
        + " FOREIGN KEY (PersonId) REFERENCES Person(id),"
        + " PRIMARY KEY (ProjectNumber, PersonRole))";
//        + " CONSTRAINT ProjectNumber_PersonRole UNIQUE (ProjectNumber, PersonRole),"
//        + " INDEX (ProjectNumber))";

    String createPersonTableSQL = "CREATE TABLE IF NOT EXISTS Person ("
        + " id int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"
        + " FirstName varchar(50),"       // First name
        + " SurName varchar(50) NOT NULL,"     // Surname name.
        + " Telephone varchar(50),"        // Telephone name.
        + " EmailAddress varchar(50),"     // Architect Email Address name.
        + " PhysicalAddress varchar(50),"  // Architect Physical Address name.
        + " PRIMARY KEY (id))";

    String createPoisedTableSQL = "CREATE TABLE IF NOT EXISTS Poised ("
//        + "id int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"
        + " ProjectNumber int(6) ZEROFILL NOT NULL,"  // Project number.
        + " ProjectName varchar(50),"  // Project name.
        + " TypeBuilding ENUM('House', 'Apartment',"
        + " 'Block', 'Store') NOT NULL," // What type of building is being designed?
                                       // E.g. House, apartment block or store, etc.
        + " PhysicalAddress varchar(200),"   // The physical address for the project.
        + " ERFNumber int(6),"         // ERF number.
        + " FeeChargedProject int(6)," // The total fee being charged for the project.
        + " PaidToDate int(6),"        // The total amount paid to date.
        + " Deadline DATE,"          // The total amount paid to date.
        + " PRIMARY KEY (ProjectNumber))";
//        + " UNIQUE (ProjectNumber))";

    String createTriggerSQL = "CREATE TRIGGER has_customer BEFORE INSERT ON Poised"
        + " FOR EACH ROW"
        + " BEGIN"
        + " IF NOT EXISTS"
        + " (SELECT 1 FROM Project WHERE"
        + " ProjectNumber = new.ProjectNumber and PersonRole = 'Customer' ) THEN"
        + " BEGIN"
        + " SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Project does not have Customer.';"
        + " END;"
        + " END IF;"
        + " END;" ;

    // String createTriggerProjectName = "CREATE TRIGGER project_name BEFORE INSERT ON Poised"
    //     + " FOR EACH ROW"
    //     + " BEGIN"
    //     + " IF new.ProjectName IS NULL THEN"
    //     + " BEGIN"
    //     + " SET new.ProjectName = "
    //     + " ( SELECT CONCAT ("
    //     + " new.TypeBuilding"
    //     + " , ' ', "
    //     + " (SELECT SurName from Person where"
    //     + " ProjectNumber = new.ProjectNumber and PersonRole = 'Customer' ))); "
    //     + " END;"
    //     + " END IF;"
    //     + " END;" ;

    String createTriggerProjectName = "CREATE TRIGGER project_name BEFORE INSERT ON Poised"
        + " FOR EACH ROW"
        + " BEGIN"
        + " IF new.ProjectName IS NULL THEN"
        + " BEGIN"
        + " SET new.ProjectName = "
        + " ( SELECT CONCAT ("
        + " new.TypeBuilding"
        + " , ' ', "
        + " (SELECT SurName from Person where"
        + " id = ( select PersonId from Project where ProjectNumber = new.ProjectNumber "
       + " and PersonRole = 'Customer' )"
        + " )));"
        + " SET new.ProjectName ="
        + " ( SELECT CONCAT (new.ProjectName,"
        + "  ' ',"
        + " ( SELECT count(ProjectName) FROM Poised"
        + " WHERE ProjectName LIKE CONCAT ( new.ProjectName , '%') ) ));"
        + " END;"
        + " END IF;"
        + " END;" ;

    // String createTriggerSQL = "CREATE TRIGGER projectName BEFORE INSERT ON Poised"
    //     + " FOR EACH ROW"
    //     + " BEGIN";

    // String createContractorTableSQL = "CREATE TABLE IF NOT EXISTS Contractor ("
    //     + "id int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"  // id number.
    //     + " Name varchar(50),"  // Contractor name.
    //     + " Telephone varchar(50),"  // Contractor Telephone name.
    //     + " EmailAddress varchar(50),"  // Contractor Email Address name.
    //     + " PhysicalAddress varchar(50),"  // Contractor Physical Address name.
    //     + " PRIMARY KEY (id))";
    //
    // String createCustomerTableSQL = "CREATE TABLE IF NOT EXISTS Customer ("
    //     + "id int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"  // id number.
    //     + " Name varchar(50),"  // Customer name.
    //     + " Telephone varchar(50),"  // Customer Telephone name.
    //     + " EmailAddress varchar(50),"  // Customer Email Address name.
    //     + " PhysicalAddress varchar(50),"  // Customer Physical Address name.
    //     + " PRIMARY KEY (id))";

    // /* Insert five record into books. */
    // String insertSQL = "INSERT INTO books VALUES "
    //    + " ( 3001, 'A Tale of Two Cities', 'Charles Dickens', 30 ),"
    //    + " ( 3002, 'Harry Potter and the Philosophers Stone', 'J.K. Rowling', 40 ),"
    //    + " ( 3003, 'The Lion, the Witch and the Wardrobe', 'C. S. Lewis', 25 ),"
    //    + " ( 3004, 'The Lord of the Rings', 'J.R.R Tolkien', 37 ),"
    //    + " ( 3005, 'Alice in Wonderland', 'Lewis Carroll', 12 )";

    /* select sql command */
    // String selectSQL = "SELECT id, Title, Author, Qty FROM books" ;

    String insertPersonSQL = "INSERT INTO Person ( SurName )"
        + " VALUES ( 'Chow' ), ( 'Chan' )";
    String insertProjectSQL = "INSERT INTO Project (PersonRole, ProjectNumber, PersonId) "
        + " VALUES ( 'Customer', 1, 1 ), ( 'Customer', 2, 2 )";
    String insertSQL = "INSERT INTO Poised ( TypeBuilding, ProjectNumber ) VALUES "
        + " ( 'House', 1 ), ( 'House', 2 )";

    MysqlHandler () {
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "otheruser",
                    "swordfish"
                    );
            // Create a direct line to the database for running our queries
            this.statement = this.connection.createStatement();

            this.statement.executeUpdate(this.createPersonTableSQL);
            this.statement.executeUpdate(this.createProjectTableSQL);
            this.statement.executeUpdate(this.createPoisedTableSQL);
            this.statement.executeUpdate(this.createTriggerSQL);
            this.statement.executeUpdate(this.createTriggerProjectName);
        } catch (SQLException e) {
            // We only want to catch a SQLException - anything else is off-limits for now.
            e.printStackTrace();
        }
    }

    /* Insert the base five records. */
    void insertIntRecord() {
        try {
            this.statement.executeUpdate(this.insertPersonSQL);
            this.statement.executeUpdate(this.insertProjectSQL);
            this.statement.executeUpdate(this.insertSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //
    // /* follow index delete the record. */
    // void deleteRecord(String idString) {
    //    try {
    //         this.statement.executeUpdate( "DELETE FROM books WHERE id = " + idString ) ;
    //    } catch (SQLException e) {
    //         e.printStackTrace();
    //    }
    // }
    //
    // /* Update the record in books table */
    // void updateRecord(String idString,
    //                 String titleString,
    //                 String authorString,
    //                 String qtyString ) {
    //    try {
    //         String updateString = "UPDATE books SET " +
    //                     "Title = '" + titleString
    //                     + "', Author = '" + authorString
    //                     + "', Qty = " + qtyString
    //                     + " WHERE id = " + idString ;
    //
    //         this.statement.executeUpdate( updateString ) ;
    //    } catch (SQLException e) {
    //         e.printStackTrace();
    //    }
    // }
    //
    // /* Insert record in books table */
    // void insertRecord(String titleString,
    //         String authorString,
    //         String qtyString) {
    //     try {
    //         String insertString = "INSERT INTO books (Title, Author, Qty) VALUES ('"
    //             + titleString + "', '" + authorString + "', " + qtyString + ")" ;
    //         System.out.println(insertString);
    //         this.statement.executeUpdate( insertString );
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // /* select from books. It is for Swing's table */
    // Vector<Vector<String>> makeRow(String sqlString) {
    //     Vector<Vector<String>> resultVector = new Vector<Vector<String>>();
    //
    //     try {
    //         ResultSet rs = this.statement.executeQuery( sqlString );
    //         while( rs.next() ){
    //             Vector<String> resultRow = new Vector<>();
    //             resultRow.add( Integer.toString(rs.getInt("id")) );
    //             resultRow.add( rs.getString("Title") );
    //             resultRow.add( rs.getString("Author") );
    //             resultRow.add( Integer.toString(rs.getInt("Qty")) );
    //             resultVector.add(resultRow);
    //         }
    //     }catch ( SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return resultVector;
    // }
    //
    // /* https://stackoverflow.com/questions/21068320/using-vector-for-jtable */
    //
    // public Vector<Vector<String>> selectRecord() {
    //     return makeRow( selectSQL );
    // }
    //
    // /* select from books for search */
    // public Vector<Vector<String>> searchRecord(
    //             String titleString,
    //             String authorString,
    //             String qtyString
    //             ) {
    //     return makeRow( "SELECT * FROM books WHERE "
    //                 + "Title like '%" + titleString + "%'"
    //                 + " and Author like '%" + authorString + "%' "
    //                 + " and Qty like '%" + qtyString + "%' "
    //         );
    // }
    //
    // /* select * from books and return the String */
    // public String toString() {
    //     String returnTxt = "";
    //     Vector<Vector<String>> resultRow = this.selectRecord();
    //     for ( int i = 0 ; i < resultRow.size() ; i ++ ) {
    //         Vector<String> resultStringArray = resultRow.get(i);
    //         for ( int j = 0 ;  j < resultStringArray.size() ; j ++ ) {
    //             returnTxt += resultStringArray.get(j) + "\t" ;
    //         }
    //         returnTxt += "\n";
    //     }
    //     return returnTxt;
    // }

    public static void main(String[] args) {
        MysqlHandler sqlHandler = new MysqlHandler();
        sqlHandler.insertIntRecord();
        // System.out.println(sqlHandler);
    }
}
