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

        + " Finalised BOOLEAN,"
        + " CompletedDate DATE,"

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

    String createProjectViewSQL = ""
        + " create or replace view ProjectView as"
        + " SELECT ProjectNumber, ProjectName, BuildingType," 
        + " PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline," 
        + " Finalised,"
        + " CompletedDate,"
        + " ( select CONCAT( id, ': ', FirstName, ' ', SurName ) "
        + " FROM Person Where id = ArchitectPId )"
        + " as Architect, "
        + " ( select CONCAT( id, ': ', FirstName, ' ', SurName ) "
	    + " FROM Person Where id = ContractorPId )"
        + " as Contractor,"
        + " ( select CONCAT( id, ': ', FirstName, ' ', SurName ) "
	    + " FROM Person Where id = CustomerPId )"
        + " as Customer,"
        + " ( select CONCAT( id, ': ', FirstName, ' ', SurName ) "
	    + " FROM Person Where id = ProjectManagerPId )"
        + " as ProjectManager, "
        + " ( select CONCAT( id, ': ', FirstName, ' ', SurName ) "
	    + " FROM Person Where id = StructuralEngineerPId )"
        + " as StructuralEngineer"
        + " FROM Project";





    // String createTriggerSQL = "CREATE TRIGGER IF NOT EXISTS has_customer BEFORE INSERT ON Project"
    //     + " FOR EACH ROW"
    //         + " BEGIN"
    //         + " IF NOT EXISTS"
    //             + " (SELECT 1 FROM Project WHERE"
    //             + " ProjectNumber = new.ProjectNumber and PersonRole = 'Customer' ) THEN"
    //             + " BEGIN"
    //                 + " SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Project does not have Customer.';"
    //             + " END;"
    //         + " END IF;"
    //     + " END;" ;
        
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

    String createTriggerProjectName = "CREATE TRIGGER IF NOT EXISTS project_name BEFORE INSERT ON Project"
        + " FOR EACH ROW"
        + " BEGIN"
        + " IF new.ProjectName IS NULL THEN"
        + " BEGIN"
        + " SET new.ProjectName = "
        + " ( SELECT CONCAT ("
        + " new.BuildingType "
        + " , ' ', "
        + " (SELECT SurName from Person where"
        // + " id = ( select CustomerPId from Project where ProjectNumber = ( "
	    // + " SELECT AUTO_INCREMENT"
        // + " FROM information_schema.tables"
        // + " WHERE table_name = 'Project'"
        // + " and table_schema = 'PoisePMS'"
        // + " )"
        // + " )"
        + " id = new.CustomerPId "
        + " )));"
        + " SET new.ProjectName ="
        + " ( SELECT CONCAT (new.ProjectName,"
        + "  ' ',"
        + " ( SELECT count(ProjectName) FROM Project"
        + " WHERE ProjectName LIKE CONCAT ( new.ProjectName , '%') ) ));"
        + " END;"
        + " END IF;"
        + " END;" ;


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
        + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer, Finalised, CompletedDate "
        + " FROM ProjectView";

    MysqlHandler () {
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    //"jdbc:mysql://127.0.0.1:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false",
                    "otheruser",
                    "swordfish"
                    );
            // Create a direct line to the database for running our queries
            this.statement = this.connection.createStatement();

            this.statement.executeUpdate(this.createPersonTableSQL);
            this.statement.executeUpdate(this.createProjectTableSQL);
            this.statement.executeUpdate(this.createProjectViewSQL);

            //this.statement.executeUpdate(this.createPoisedTableSQL);
            //this.statement.executeUpdate(this.createTriggerSQL);
            this.statement.executeUpdate(this.createTriggerProjectName);
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
            personTagList.addElement( rs.getString("Result") );
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
            String projectName,
            String buildingType,
            String physicalAddress,
            String eRFNumber,
            String feeCharged,
            String paidToDate,
            String deadLine,
            String architectPId,
            String contractorPId,
            String customerPId,
            String projectManagerPId,
            String structuralEngineerPId,
            String isFinalised,
            String completedDate

            ) throws SQLException {

        String sqlStr = "INSERT INTO Project ( "

       	+ " BuildingType, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"
        + " ArchitectPId, ContractorPId, CustomerPId, ProjectManagerPId, StructuralEngineerPId,"
        + " Finalised";

        if ( !completedDate.equals("")) {
            sqlStr += ", CompletedDate";
        };

        if ( !projectName.equals("")) {
            sqlStr += ", ProjectName";
        }

        sqlStr += " )"
            + "VALUES (" 
            + "'" + buildingType + "', "
            + "'" + physicalAddress + "', "
            + "'" + eRFNumber + "', "
            + "'" + feeCharged + "', "
            + "'" + paidToDate + "', "
            + "'" + deadLine + "', "
            + "'" + architectPId + "', "
            + "'" + contractorPId + "', "
            + "'" + customerPId + "', "
            + "'" + projectManagerPId + "', "
            + "'" + structuralEngineerPId + "', "
            + "'" + isFinalised + "' " ;

            if ( !completedDate.equals("") ) {
                sqlStr += ", '" + completedDate + "' ";
            }

            if ( !projectName.equals("")) {
                sqlStr += ", '" +  projectName + "' ";
            }

            sqlStr += ")";
        System.out.println(sqlStr);
        this.statement.executeUpdate(sqlStr);
    }
    
    void updateProjectRecord (
            String projectNo,
            String projectName,
            String buildingType,
            String physicalAddress,
            String eRFNumber,
            String feeCharged,
            String paidToDate,
            String deadline,
            String architectPId,
            String contractorPId,
            String customerPId,
            String projectManagerPId,
            String structuralEngineerPId,
            String completedDate,
            String isFinalised
            ) throws SQLException {

        String sqlStr = "UPDATE Project SET "
            + " ProjectName = '" + projectName + "', "
            + " BuildingType = '" + buildingType + "',"
            + " PhysicalAddress = '" + physicalAddress + "',"
            + " ERFNumber = '" + eRFNumber + "',"
            + " FeeCharged = '" + feeCharged + "',"
            + " PaidToDate = '" + paidToDate + "',"
            + " Deadline = '" + deadline + "',"
            + " ArchitectPId = '" + architectPId  + "', "
            + " ContractorPId = '" + contractorPId + "', "
            + " CustomerPId = '" + customerPId + "', "
            + " ProjectManagerPId = '" + projectManagerPId + "', " 
            + " StructuralEngineerPId = '" +  structuralEngineerPId + "', "
            + " Finalised = '" + isFinalised + "'";

            if ( !completedDate.equals("") ) {
                sqlStr += ", CompletedDate = '" + completedDate + "',";
            };

            sqlStr += " WHERE ProjectNumber = '" + projectNo + "'";
        
        this.statement.executeUpdate(sqlStr);
    }
    
    void deletePerson (String id) throws SQLException {
        String sqlStr = "DELETE FROM Person"
            + " WHERE id ="
            + " '" + id + "'" ;
        this.statement.executeUpdate(sqlStr); 
    }
    
    void deleteProject (String id) throws SQLException {
        String sqlStr = "DELETE FROM Project"
            + " WHERE ProjectNumber ="
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

    Vector<Vector<String>> searchProjectRecord(
        String projectNameVal,
        String physicalAddressVal, 
        String erfNoVal,
        String feeChargedVal,
        String paidTodateVal,
        String buildingTypeVal,
        String deadLine,
        String architectVal,
        String contractorVal,
        String customerVal,
        String managerVal,
        String engineerVal,
        String completedDate,
        String isFinalised
    ) {
        return makeProjectRow(  "SELECT "
            + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged,"
            + " PaidToDate, Deadline,"
            + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer,"
            + " Finalised, CompletedDate"
            + " FROM ProjectView WHERE "
            +     " ProjectName like '%"       + projectNameVal + "%'"
            + " AND BuildingType LIKE '%"      + buildingTypeVal + "%'"
            + " AND PhysicalAddress LIKE '%"   + physicalAddressVal + "%'"
            + " AND ERFNumber LIKE '%"         + erfNoVal + "%'" 
            + " AND FeeCharged LIKE '%"        + feeChargedVal + "%'"
            + " AND PaidToDate LIKE '%"        + paidTodateVal + "%'"
            + " AND Deadline LIKE '%"          + deadLine + "%'"
            + " AND Architect LIKE '%"         + architectVal + "%'" 
            + " AND Contractor LIKE '%"        + contractorVal + "%'"
            + " AND Customer LIKE '%"          + customerVal + "%'"
            + " AND ProjectManager LIKE '%"     + managerVal + "%'"
            + " AND StructuralEngineer LIKE '%" + engineerVal + "%'"
            + " AND CompletedDate LIKE '%"      + completedDate + "%'"
            + " AND Finalised LIKE '%"          + isFinalised + "%'"

        //+ " Finalised BOOLEAN,"
        //+ " CompletedDate DATE,"

             );
    };

    
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
                resultRow.add( rs.getString("Architect") );
                resultRow.add( rs.getString("Contractor") );
                resultRow.add( rs.getString("Customer") );
                resultRow.add( rs.getString("ProjectManager") );
                resultRow.add( rs.getString("StructuralEngineer") );
                String isFinalisedTxt;
                if ( rs.getBoolean("Finalised") ) {
                    isFinalisedTxt = "Yes";
                } else {
                    isFinalisedTxt = "No";
                }
                resultRow.add( isFinalisedTxt );
                if ( rs.getDate("CompletedDate") != null ) { 
                    resultRow.add( rs.getDate("CompletedDate").toString() );
                }
                resultVector.add(resultRow);
            }
        }catch ( SQLException e) {
            e.printStackTrace();
        }
        return resultVector;
    }

    void finalisedProject(String completedDate, String projectNo) throws SQLException {
        String sqlStr = "UPDATE Project SET Finalised = 1, "
        + " CompletedDate = '" + completedDate + "'"
        + " Where ProjectNumber = '" + projectNo + "'" ;
        // System.out.println(sqlStr);
        this.statement.executeUpdate(sqlStr);
    }
    
    public static void main(String[] args) {
        MysqlHandler sqlHandler = new MysqlHandler();
        sqlHandler.insertIntRecord();
        // System.out.println(sqlHandler);
    }
}
