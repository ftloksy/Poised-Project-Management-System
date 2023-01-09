import java.sql.*;
import java.util.Vector;
//import java.util.ArrayList;

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
        + " ProjectNumber int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"
        + " ProjectName varchar(50),"  // Project name.
        + " BuildingType ENUM('House', 'Apartment',"
        + " 'Block', 'Store') NOT NULL," // What type of building is being designed?
                                       // E.g. House, apartment block or store, etc.
        + " PhysicalAddress varchar(200),"
        + " ERFNumber int(6) UNSIGNED,"

        + " FeeCharged int(6)," // The total fee being charged for the project.
        + " PaidToDate int(6),"        // The total amount paid to date.
        + " Deadline DATE,"          // The total amount paid to date

        + " ArchitectPId int(6) UNSIGNED,"
        + " ContractorPId int(6) UNSIGNED,"
        + " CustomerPId int(6) UNSIGNED,"
        + " ProjectManagerPId int(6) UNSIGNED,"
        + " StructuralEngineerPId int(6) UNSIGNED,"

        + " PRIMARY KEY (ProjectNumber),"
        + " FOREIGN KEY (ArchitectPId) REFERENCES Person(id),"
        + " FOREIGN KEY (ContractorPId) REFERENCES Person(id),"
        + " FOREIGN KEY (CustomerPId) REFERENCES Person(id),"
        + " FOREIGN KEY (ProjectManagerPId) REFERENCES Person(id),"
        + " FOREIGN KEY (StructuralEngineerPId) REFERENCES Person(id))";

    String createPersonTableSQL = "CREATE TABLE IF NOT EXISTS Person ("
        + " id int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"
        + " FirstName varchar(50),"       // First name
        + " SurName varchar(50) NOT NULL,"     // Surname name.
        + " Telephone varchar(50),"        // Telephone name.
        + " EmailAddress varchar(50),"     // Architect Email Address name.
        + " PhysicalAddress varchar(50),"  // Architect Physical Address name.
        + " PRIMARY KEY (id))";

    //String createPoisedTableSQL = "CREATE TABLE IF NOT EXISTS Poised ("
        //+ " ERFNumber int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"         // ERF number.
        //+ " ProjectNumber int(6) UNSIGNED,"  // Project number.

        //+ " PhysicalAddress varchar(200),"   // The physical address for the project.

//.
        //+ " FOREIGN KEY (ProjectNumber) REFERENCES Project(ProjectNumber),"
        //+ " PRIMARY KEY (ERFNumber))";

    //String createTriggerSQL = "CREATE TRIGGER IF NOT EXISTS has_customer BEFORE INSERT ON Poised"
        //+ " FOR EACH ROW"
        //+ " BEGIN"
        //+ " IF NOT EXISTS"
        //+ " (SELECT 1 FROM Project WHERE"
        //+ " ProjectNumber = new.ProjectNumber and PersonRole = 'Customer' ) THEN"
        //+ " BEGIN"
        //+ " SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Project does not have Customer.';"
        //+ " END;"
        //+ " END IF;"
        //+ " END;" ;
        
    String createTriggerPersonSurNameInsertSQL = "CREATE TRIGGER IF NOT EXISTS insert_has_surname BEFORE INSERT ON Person"
        + " FOR EACH ROW"
        + " BEGIN"
        + " IF EXISTS"
        + " (SELECT 1 FROM Person WHERE"
        + " new.SurName = '' ) THEN"
        + " BEGIN"
        + " SIGNAL SQLSTATE '45011' SET MESSAGE_TEXT = 'Person does not have SurName.';"
        + " END;"
        + " END IF;"
        + " END;";

    String createTriggerPersonSurNameUpdateSQL = "CREATE TRIGGER IF NOT EXISTS update_has_surname BEFORE UPDATE ON Person"
        + " FOR EACH ROW"
        + " BEGIN"
        + " IF EXISTS"
        + " (SELECT 1 FROM Person WHERE"
        + " new.SurName = '' ) THEN"
        + " BEGIN"
        + " SIGNAL SQLSTATE '45012' SET MESSAGE_TEXT = 'Person does not have SurName.';"
        + " END;"
        + " END IF;"
        + " END;" ;
        
    String selectPersonTag = "SELECT CONCAT( id, ': ', FirstName, ' ', SurName ) AS Result FROM Person" ;

    //String createTriggerProjectName = "CREATE TRIGGER IF NOT EXISTS project_name BEFORE INSERT ON Poised"
        //+ " FOR EACH ROW"
        //+ " BEGIN"
        //+ " IF new.ProjectName IS NULL THEN"
        //+ " BEGIN"
        //+ " SET new.ProjectName = "
        //+ " ( SELECT CONCAT ("
        //+ " new.TypeBuilding"
        //+ " , ' ', "
        //+ " (SELECT SurName from Person where"
        //+ " id = ( select PersonId from Project where ProjectNumber = new.ProjectNumber "
        //+ " and PersonRole = 'Customer' )"
        //+ " )));"
        //+ " SET new.ProjectName ="
        //+ " ( SELECT CONCAT (new.ProjectName,"
        //+ "  ' ',"
        //+ " ( SELECT count(ProjectName) FROM Poised"
        //+ " WHERE ProjectName LIKE CONCAT ( new.ProjectName , '%') ) ));"
        //+ " END;"
        //+ " END IF;"
        //+ " END;" ;

    String insertPersonSQL = "INSERT INTO Person ( SurName )"
        + " VALUES ( 'Chow' ), ( 'Chan' )";
    //String insertProjectSQL = "INSERT INTO Project (PersonRole, ProjectNumber, PersonId) "
        //+ " VALUES ( 'Customer', 1, 1 ), ( 'Customer', 2, 2 )";
    //String insertSQL = "INSERT INTO Poised ( TypeBuilding, ProjectNumber ) VALUES "
        //+ " ( 'House', 1 ), ( 'House', 2 )";
        
    String selectPerson = "SELECT  id, FirstName, SurName, Telephone, EmailAddress, PhysicalAddress"
        + " FROM Person";

    String selectProject = "SELECT "
        + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"
        + " ArchitectPId, ContractorPId, CustomerPId, ProjectManagerPId, StructuralEngineerPId"
        + " FROM Project";

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
            //this.statement.executeUpdate(this.createPoisedTableSQL);
            //this.statement.executeUpdate(this.createTriggerSQL);
            //this.statement.executeUpdate(this.createTriggerProjectName);
            this.statement.executeUpdate(this.createTriggerPersonSurNameInsertSQL);
            this.statement.executeUpdate(this.createTriggerPersonSurNameUpdateSQL);
        } catch (SQLException e) {
            // We only want to catch a SQLException - anything else is off-limits for now.
            e.printStackTrace();
        }
    }

    /* Insert the base five records. */
    void insertIntRecord() {
        try {
            this.statement.executeUpdate(this.insertPersonSQL);
            //this.statement.executeUpdate(this.insertProjectSQL);
            //this.statement.executeUpdate(this.insertSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    Vector<String> getPersonList() throws SQLException {
        return this.getResultList( this.selectPersonTag );
    }
    
    Vector<String> getResultList(String sqlString) throws SQLException {
        
        ResultSet rs = this.statement.executeQuery( sqlString );
        Vector<String> personTagList = new Vector<>();
        
        while( rs.next() ){
            personTagList.add( rs.getString("Result") );
        }
        
        return personTagList;
            
    }
    
    void insertPersonRecord (
            String firstName,
            String surName,
            String telePhone,
            String emailAddress,
            String physicalAddress ) throws SQLException {

        String sqlStr = "INSERT INTO Person ( FirstName, SurName, "
            + "Telephone, EmailAddress, PhysicalAddress ) "
            + "VALUES (" 
            + "'" + firstName + "', "
            + "'" + surName + "', "
            + "'" + telePhone + "', "
            + "'" + emailAddress + "', "
            + "'" + physicalAddress + "' "
            + ")";
        this.statement.executeUpdate(sqlStr);
    }
    
        void insertProjectRecord (
            String ProjectName,
            String BuildingType,
            String PhysicalAddress,
            String ERFNumber,
            String FeeCharged,
            String PaidToDate,
            String Deadline,
            String ArchitectPId,
            String ContractorPId,
            String CustomerPId,
            String ProjectManagerPId,
            String StructuralEngineerPId
            ) throws SQLException {

        String sqlStr = "INSERT INTO Project ( "

       	+ " ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"
        + " ArchitectPId, ContractorPId, CustomerPId, ProjectManagerPId, StructuralEngineerPId"

            + " )"
            + "VALUES (" 
            + "'" + ProjectName + "', "
            + "'" + BuildingType + "', "
            + "'" + PhysicalAddress + "', "
            + "'" + ERFNumber + "', "
            + "'" + FeeCharged + "', "
            + "'" + PaidToDate + "', "
            + "'" + Deadline + "', "
            + "'" + ArchitectPId + "', "
            + "'" + ContractorPId + "', "
            + "'" + CustomerPId + "', "
            + "'" + ProjectManagerPId + "', "
            + "'" + StructuralEngineerPId + "' "
            + ")";
        System.out.println(sqlStr);
        this.statement.executeUpdate(sqlStr);
    }
    
    void deletePerson (String id) throws SQLException {
        String sqlStr = "DELETE FROM Person"
            + " WHERE id ="
            + " '" + id + "'" ;
        this.statement.executeUpdate(sqlStr); 
    }
    
    void updatePersonRecord (
            String id,
            String firstName,
            String surName,
            String telePhone,
            String emailAddress,
            String physicalAddress    
        ) throws SQLException {
        String sqlStr = "UPDATE Person SET"
            + " FirstName = '" + firstName + "',"
            + " SurName = '" + surName + "',"
            + " Telephone = '" + telePhone + "',"
            + " EmailAddress = '" + emailAddress + "',"
            + " PhysicalAddress = '" + physicalAddress + "'"
            + " WHERE id = '" + id + "'";
        this.statement.executeUpdate(sqlStr); 
    }
    
    Vector<Vector<String>> searchPersonRecord(
            String firstName,
            String surName,
            String telePhone,
            String emailAddress,
            String physicalAddress      
        ) {
        return makePersonRow( "SELECT * FROM Person WHERE"
                    + " FirstName like '%" + firstName + "%'"
                    + " AND SurName like '%" + surName + "%' "
                    + " AND Telephone like '%" + telePhone + "%' "
                    + " AND EmailAddress like '%" + emailAddress + "%' "
                    + " AND PhysicalAddress like '%" + physicalAddress + "%' "
            );
    }
    
    Vector<Vector<String>> selectPersonRecord() {
        return this.makePersonRow( this.selectPerson );
    }
    
    Vector<Vector<String>> selectProjectRecord() {
        return this.makeProjectRow( this.selectProject );
    }
    
    Vector<Vector<String>> makePersonRow(String sqlString) {
        Vector<Vector<String>> resultVector = new Vector<Vector<String>>();

        try {
            ResultSet rs = this.statement.executeQuery( sqlString );
            
            while( rs.next() ){
                Vector<String> resultRow = new Vector<>();
                resultRow.add( Integer.toString(rs.getInt("id")) );
                resultRow.add( rs.getString("FirstName") );
                resultRow.add( rs.getString("SurName") );
                resultRow.add( rs.getString("Telephone") );
                resultRow.add( rs.getString("EmailAddress") );
                resultRow.add( rs.getString("PhysicalAddress") );
                resultVector.add(resultRow);
            }
            
        }catch ( SQLException e) {
            e.printStackTrace();
        }
        return resultVector;
    }
    
    Vector<Vector<String>> makeProjectRow(String sqlString) {
        Vector<Vector<String>> resultVector = new Vector<Vector<String>>();

        try {
            ResultSet rs = this.statement.executeQuery( sqlString );
            while( rs.next() ){
                Vector<String> resultRow = new Vector<>();
                resultRow.add( Integer.toString( rs.getInt("ProjectNumber")) );
                resultRow.add( rs.getString("ProjectName") );
                resultRow.add( rs.getString("BuildingType") );
                resultRow.add( rs.getString("PhysicalAddress") );
                resultRow.add( Integer.toString( rs.getInt("ERFNumber")) );
                resultRow.add( Integer.toString( rs.getInt("FeeCharged")) );
                resultRow.add( Integer.toString( rs.getInt("PaidToDate")) );
                resultRow.add( rs.getDate("Deadline").toString() );
                resultRow.add( Integer.toString( rs.getInt("ArchitectPId")) );
                resultRow.add( Integer.toString( rs.getInt("ContractorPId")) );
                resultRow.add( Integer.toString( rs.getInt("CustomerPId")) );
                resultRow.add( Integer.toString( rs.getInt("ProjectManagerPId")) );
                resultRow.add( Integer.toString( rs.getInt("StructuralEngineerPId")) );
                resultVector.add(resultRow);
            }
        }catch ( SQLException e) {
            e.printStackTrace();
        }
        return resultVector;
    }
    
    public static void main(String[] args) {
        MysqlHandler sqlHandler = new MysqlHandler();
        sqlHandler.insertIntRecord();
        // System.out.println(sqlHandler);
    }
}
