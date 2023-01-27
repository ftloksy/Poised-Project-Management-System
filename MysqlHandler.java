import java.sql.*;
import java.util.Vector;

/**
 * This class is handle mysql require.
 * Include create table, Insert, delete, update and select.
 * 
 * <ul>
 *  <li>createProjectTableSQL :  Create Project table</li>
 *  <li>createPersonTableSQL :  Create Person Table</li>
 *  <li>createProjectViewSQL :  Create a PersonView for display Person table information to Person JTable.</li>
 *  <li>createTriggerPersonSurNameInsertSQL :  Make Sure When User insert data to Person, SurName isn't '', 
 *          because in gui always will add this value to data table. </li>
 *  <li>createTriggerPersonSurNameUpdateSQL :  Make Sure When User update data to Person, SurName isn't '', 
 *          because in gui always will add this value to data table. </li>
 *   <li>selectPersonTag :   Create a select for DefaultComboBoxModel, for user to select  ArchitectPId, ContractorPId ...  </li>
 *   <li>createTriggerProjectName :  Create a trigger for 
 *      <pre>
 *     -------
 *      If a project name is not provided
 *      when the information is captured, name the project using the surname of
 *      the customer. For example, a house being built by Mike Tyson would be
 *      called “House Tyson” and an apartment block owned by Jared Goldman
 *      would be called “Apartment Goldman”.
 *     -------
 *       </pre></li>
 *   <li>selectPerson :  Select all information from Person for display in Person Table.</li>
 *   <li>selectProject :  Select all information from ProjectView for display in Project Table. </li>
 *   <li>selectNeedCompletedProject :  Select all information from Project, that it isn't finalised. </li>
 * <ul>
 * 
 * @author      Frankie Chow
 * @version     2023-1-23
 */
public class MysqlHandler {
    Connection connection = null ;
    Statement statement = null ;

    /** Create Project table */
    private String createProjectTableSQL = "CREATE TABLE IF NOT EXISTS Project ("
        + " ProjectNumber int(6) ZEROFILL NOT NULL AUTO_INCREMENT," // Project Number
        + " ProjectName varchar(50),"  // Project name.
        + " BuildingType "  // What type of building is being designed? 
        + " ENUM('House', 'Apartment',"
        + " 'Block', 'Store') NOT NULL," // What type of building is being designed?
                                       // E.g. House, apartment block or store, etc.
        + " PhysicalAddress varchar(200),"
        + " ERFNumber varchar(30),"

        + " FeeCharged int(6)," // The total fee being charged for the project.
        + " PaidToDate int(6),"        // The total amount paid to date.
        + " Deadline DATE,"          // The Project's Deadline.

        + " ArchitectPId int(6) UNSIGNED,"   // The Architect for the project.
        + " ContractorPId int(6) UNSIGNED,"  // The Contractor for the project.
        + " CustomerPId int(6) UNSIGNED,"    // The Customer for the project.
        + " ProjectManagerPId int(6) UNSIGNED,"  // The Project Manager for the project.
        + " StructuralEngineerPId int(6) UNSIGNED," // The Structural Engineer of the project.

        + " Finalised BOOLEAN,"   // The Project is Finalised ?
        + " CompletedDate DATE,"  // When is project has completed.

        + " PRIMARY KEY (ProjectNumber),"
        + " UNIQUE (ERFNumber),"        // ERFNumber is unique.
        + " FOREIGN KEY (ArchitectPId) REFERENCES Person(id),"
        + " FOREIGN KEY (ContractorPId) REFERENCES Person(id),"
        + " FOREIGN KEY (CustomerPId) REFERENCES Person(id),"
        + " FOREIGN KEY (ProjectManagerPId) REFERENCES Person(id),"
        + " FOREIGN KEY (StructuralEngineerPId) REFERENCES Person(id))";

    /** Create Person Table */
    private String createPersonTableSQL = "CREATE TABLE IF NOT EXISTS Person ("
        + " id int(6) ZEROFILL NOT NULL AUTO_INCREMENT,"
        + " FirstName varchar(50),"       // First name
        + " SurName varchar(50) NOT NULL,"     // Surname name.
        + " Telephone varchar(50),"        // Telephone name.
        + " EmailAddress varchar(50),"     // Architect Email Address name.
        + " PhysicalAddress varchar(50),"  // Architect Physical Address name.
        + " UNIQUE (EmailAddress),"        // Email Address is unique.
        + " PRIMARY KEY (id))";

    /** Create a PersonView for display Person table information to Person JTable. */
    private String createProjectViewSQL = ""
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

    /**
     * Make Sure When User insert data to Person, SurName isn't '', 
     * because in gui always will add this value to data table.
     */
    private String createTriggerPersonSurNameInsertSQL = "CREATE TRIGGER IF NOT EXISTS insert_has_surname BEFORE INSERT ON Person"
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

    /** 
     * Make Sure When User update data to Person, SurName isn't '', 
     * because in gui always will add this value to data table.
     */
    private String createTriggerPersonSurNameUpdateSQL = "CREATE TRIGGER IF NOT EXISTS update_has_surname BEFORE UPDATE ON Person"
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
        
    /** Create a select for DefaultComboBoxModel, for user to select  ArchitectPId, ContractorPId ...  */
    private String selectPersonTag = "SELECT CONCAT( id, ': ', FirstName, ' ', SurName ) AS Result FROM Person" ;

    /** Create a trigger for 
     * -------
     *  If a project name is not provided
     * when the information is captured, name the project using the surname of
     * the customer. For example, a house being built by Mike Tyson would be
     * called “House Tyson” and an apartment block owned by Jared Goldman
     * would be called “Apartment Goldman”.
     * -------
     */
    private String createTriggerProjectName = "CREATE TRIGGER IF NOT EXISTS project_name BEFORE INSERT ON Project"
        + " FOR EACH ROW"
        + " BEGIN"
           + " IF new.ProjectName IS NULL THEN"
           + " BEGIN"
              + " SET new.ProjectName = "
              + " ( SELECT CONCAT ("
              + " new.BuildingType "
              + " , ' ', "
              + " (SELECT SurName from Person "
              + " where"
              + " id = new.CustomerPId "
              + " )));"
                /*
                 * When a Project Name is 'House Chan', than another customer 'May Chan'
                 * Want to building another House. It will set the Project Name to
                 * 'House Chan 1'.
                 */ 
              + " SET @countProjectNameExist = ( SELECT count(ProjectName) FROM Project WHERE"
              + " ProjectName = new.ProjectName"
              + " OR ProjectName REGEXP CONCAT ( '^' , new.ProjectName , ' [0-9]*') );"
              + " If  @countProjectNameExist > 0  THEN"
                + " BEGIN"
                  + "  SET new.ProjectName = ( SELECT CONCAT (new.ProjectName,  ' ',  @countProjectNameExist )); "
                + " END;"
              + " END IF;"
            + " END;"
            + " END IF;"
        + " END;" ;

    /**
     * When user delete a record in Project, 
     * Delete data about projects and people associated with them.
     * But If the people is still ref to another project.
     * the delete will cancel. 
     */
    private String createTriggerDeletePeopleAssociated = ""
+ " CREATE TRIGGER IF NOT EXISTS delete_people_associated "
+ "     AFTER DELETE "
+ "     ON Project FOR EACH ROW "
+ "     BEGIN "
+ "             SET @ExistContractor = ( SELECT 'Exist' FROM Project "
+ "                     WHERE "
+ "                             ArchitectPId = OLD.ContractorPId "
+ "                     OR      ContractorPId = OLD.ContractorPId "
+ "                     OR      CustomerPId = OLD.ContractorPId "
+ "                     OR      ProjectManagerPId = OLD.ContractorPId "
+ "                     OR      StructuralEngineerPId = OLD.ContractorPId "
+ "                     ) ; "
+ "  "
+ "             SET @ExistCustomer = ( SELECT 'Exist' FROM Project "
+ "                     WHERE "
+ "                             ArchitectPId = OLD.CustomerPId "
+ "                     OR      ContractorPId = OLD.CustomerPId "
+ "                     OR      CustomerPId = OLD.CustomerPId "
+ "                     OR      ProjectManagerPId = OLD.CustomerPId "
+ "                     OR      StructuralEngineerPId = OLD.CustomerPId "
+ "                     ) ; "
+ "  "
+ "             SET @ExistArchitect = ( SELECT 'Exist' FROM Project "
+ "                     WHERE "
+ "                             ArchitectPId = OLD.ArchitectPId "
+ "                     OR      ContractorPId = OLD.ArchitectPId "
+ "                     OR      CustomerPId = OLD.ArchitectPId "
+ "                     OR      ProjectManagerPId = OLD.ArchitectPId "
+ "                     OR      StructuralEngineerPId = OLD.ArchitectPId "
+ "                     ) ; "
+ "  "
+ "             SET @ExistProjectManager = ( SELECT 'Exist' FROM Project "
+ "                     WHERE "
+ "                             ArchitectPId = OLD.ProjectManagerPId "
+ "                     OR      ContractorPId = OLD.ProjectManagerPId "
+ "                     OR      CustomerPId = OLD.ProjectManagerPId "
+ "                     OR      ProjectManagerPId = OLD.ProjectManagerPId "
+ "                     OR      StructuralEngineerPId = OLD.ProjectManagerPId "
+ "                     ) ; "
+ "  "
+ "             SET @ExistStructuralEngineer = ( SELECT 'Exist' FROM Project "
+ "                     WHERE "
+ "                             ArchitectPId = OLD.StructuralEngineerPId "
+ "                     OR      ContractorPId = OLD.StructuralEngineerPId "
+ "                     OR      CustomerPId = OLD.StructuralEngineerPId "
+ "                     OR      ProjectManagerPId = OLD.StructuralEngineerPId "
+ "                     OR      StructuralEngineerPId = OLD.StructuralEngineerPId "
+ "                     ) ; "
+ "  "
+ "             If @ExistContractor IS NULL THEN "
+ "       DELETE FROM Person WHERE id = OLD.ContractorPId ; "
+ "             END IF; "
+ "  "
+ "             If @ExistCustomer IS NULL THEN "
+ "       DELETE FROM Person WHERE id = OLD.CustomerPId ; "
+ "             END IF; "
+ "  "
+ "             If @ExistArchitect IS NULL THEN "
+ "       DELETE FROM Person WHERE id = OLD.ArchitectPId ; "
+ "             END IF; "
+ "  "
+ "             If @ExistProjectManager IS NULL THEN "
+ "       DELETE FROM Person WHERE id = OLD.ProjectManagerPId ; "
+ "             END IF; "
+ "  "
+ "             If @ExistStructuralEngineer IS NULL THEN "
+ "       DELETE FROM Person WHERE id = OLD.StructuralEngineerPId ; "
+ "             END IF; "
+ "     END; ";


    /* Select all information from Person for display in Person Table. */
    String selectPerson = "SELECT  id, FirstName, SurName, Telephone, EmailAddress, PhysicalAddress"
        + " FROM Person";

    /* Select all information from ProjectView for display in Project Table. */
    String selectProject = "SELECT "
        + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"
        + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer, Finalised, CompletedDate "
        + " FROM ProjectView";

    /** Select all information from Project, that it isn't finalised. */
    private String selectNeedCompletedProject = "SELECT "
        + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged, PaidToDate, Deadline,"
        + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer, Finalised, CompletedDate "
        + " FROM ProjectView"
        + " WHERE Finalised = 0";

    /**
     * MysqlHandler constructor
     * Connect to the PoisedPMS database
     * And Query and Update database table.
     * 
     * @throws SQLException If the database cannot connection or query at database or table.
     */   
    public MysqlHandler () {
        try {
            // Connect to the PoisedPMS database, via the jdbc:mysql: channel on localhost (this PC)
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
            this.statement.executeUpdate(this.createProjectViewSQL);

            this.statement.executeUpdate(this.createTriggerProjectName);
            this.statement.executeUpdate(this.createTriggerPersonSurNameInsertSQL);
            this.statement.executeUpdate(this.createTriggerPersonSurNameUpdateSQL);
            this.statement.executeUpdate(this.createTriggerDeletePeopleAssociated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * Select person information for DefaultComboBoxModel for choice ArchitectPId, ContractorPId ...  
     *
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result 
     */
    public Vector<String> getPersonList() throws SQLException {
        return this.getResultList( this.selectPersonTag );
    }
    
    /**
     * Follow the SqlStr to select from database, and it just return one column.
     * 
     * @param sqlString follow this string to query database.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result (this is just a column. )
     */
    public Vector<String> getResultList(String sqlString) throws SQLException {
        
        ResultSet rs = this.statement.executeQuery( sqlString );
        Vector<String> personTagList = new Vector<>();
        
        while( rs.next() ){
            personTagList.addElement( rs.getString("Result") );
        }
        
        return personTagList;
            
    }

    /**
     * Enter new Person record to Person data table.
     * 
     * @param firstName this is person's First Name.
     * @param surName this is person's SurName.
     * @param telePhone this is person's telephone.
     * @param emailAddress this is person's email address.
     * @param physicalAddress this is person's physical address.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void insertPersonRecord (
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
    
    /** 
     * Enter new Project record to Project data table.
     * 
     * @param projectName Project's Name.
     * @param buildingType Project Building's Type.
     * @param physicalAddress Project Building's Physical Address.
     * @param eRFNumber Project Building's erf number.
     * @param feeCharged The total fee being charged for the project.
     * @param paidToDate The total amount paid to date.
     * @param deadLine Deadline for the project.
     * @param architectPId The Person id ( ref Person table ) of the architect for the project.
     * @param contractorPId The Person id ( ref Person table ) of the contractor for the project.
     * @param customerPId The Person id ( ref Person table ) of the customer for the project.
     * @param projectManagerPId The Person id ( ref Person table ) of the Project Manager for the project.
     * @param structuralEngineerPId The Person id ( ref Person table ) of the Structural Engineer for the project.
     * @param isFinalised The Project is Finalised.
     * @param completedDate The Project's complete date.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void insertProjectRecord (
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
        this.statement.executeUpdate(sqlStr);
    }
    
    /** 
     * Follow ProjectNumber to update exist Project record. 
     *  
     * @param projectNo Project number.
     * @param projectName Project's Name.
     * @param buildingType Project Building's Type.
     * @param physicalAddress Project Building's Physical Address.
     * @param eRFNumber Project Building's erf number.
     * @param feeCharged The total fee being charged for the project.
     * @param paidToDate The total amount paid to date.
     * @param deadLine Deadline for the project.
     * @param architectPId The Person id ( ref Person table ) of the architect for the project.
     * @param contractorPId The Person id ( ref Person table ) of the contractor for the project.
     * @param customerPId The Person id ( ref Person table ) of the customer for the project.
     * @param projectManagerPId The Person id ( ref Person table ) of the Project Manager for the project.
     * @param structuralEngineerPId The Person id ( ref Person table ) of the Structural Engineer for the project.
     * @param isFinalised The Project is Finalised.
     * @param completedDate The Project's complete date.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void updateProjectRecord (
            String projectNo,
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
            + " Deadline = '" + deadLine + "',"
            + " ArchitectPId = '" + architectPId  + "', "
            + " ContractorPId = '" + contractorPId + "', "
            + " CustomerPId = '" + customerPId + "', "
            + " ProjectManagerPId = '" + projectManagerPId + "', " 
            + " StructuralEngineerPId = '" +  structuralEngineerPId + "', "
            + " Finalised = '" + isFinalised + "'";

        if ( !completedDate.equals("") ) {
            sqlStr += ", CompletedDate = '" + completedDate + "'";
        };

        sqlStr += " WHERE ProjectNumber = '" + projectNo + "'";
        this.statement.executeUpdate(sqlStr);
    }
    
    /** 
     * Follow id to delete exist Person record. 
     * 
     * @param id Person's record id.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void deletePerson (String id) throws SQLException {
        String sqlStr = "DELETE FROM Person"
            + " WHERE id ="
            + " '" + id + "'" ;
        this.statement.executeUpdate(sqlStr); 
    }
    
    /** 
     * Follow ProjectNumber to delete exist Project record. 
     * 
     * @param id Person's record id.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void deleteProject (String id) throws SQLException {
        String sqlStr = "DELETE FROM Project"
            + " WHERE ProjectNumber ="
            + " '" + id + "'" ;
        this.statement.executeUpdate(sqlStr); 
    }
    
    /** 
     * Follow id to update exist Person record. 
     * 
     * @param id Person's record id.
     * @param firstName this is person's First Name.
     * @param surName this is person's SurName.
     * @param telePhone this is person's telephone.
     * @param emailAddress this is person's email address.
     * @param physicalAddress this is person's physical address.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void updatePersonRecord (
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
    
    /**
     * User can type in the PersonEditor any JTextField
     * to search record in Person table in database.
     * User key in "Frank" in FirstName field.
     * It will return "Frankie" FirstName's and "Franky" FirstName's rows.
     * 
     * @param firstName this is person's First Name.
     * @param surName this is person's SurName.
     * @param telePhone this is person's telephone.
     * @param emailAddress this is person's email address.
     * @param physicalAddress this is person's physical address.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> searchPersonRecord(
            String firstName,
            String surName,
            String telePhone,
            String emailAddress,
            String physicalAddress      
        ) throws SQLException {
        return makePersonRow( "SELECT * FROM Person WHERE"
                    + " FirstName like '%" + firstName + "%'"
                    + " AND SurName like '%" + surName + "%' "
                    + " AND Telephone like '%" + telePhone + "%' "
                    + " AND EmailAddress like '%" + emailAddress + "%' "
                    + " AND PhysicalAddress like '%" + physicalAddress + "%' "
            );
    }

    /**
     * User can type in the ProjectEditor any JTextField
     * to search record in Project table in database.
     * It is like searchPersonRecord method.
     * 
     * @param projectName Project's Name.
     * @param buildingType Project Building's Type.
     * @param physicalAddress Project Building's Physical Address.
     * @param eRFNumber Project Building's erf number.
     * @param feeCharged The total fee being charged for the project.
     * @param paidToDate The total amount paid to date.
     * @param deadLine Deadline for the project.
     * @param architectPId The Person id ( ref Person table ) of the architect for the project.
     * @param contractorPId The Person id ( ref Person table ) of the contractor for the project.
     * @param customerPId The Person id ( ref Person table ) of the customer for the project.
     * @param projectManagerPId The Person id ( ref Person table ) of the Project Manager for the project.
     * @param structuralEngineerPId The Person id ( ref Person table ) of the Structural Engineer for the project.
     * @param isFinalised The Project is Finalised.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> searchProjectRecord (
        // String projectNoVal,
        String projectName,
        String physicalAddress, 
        String eRFNumber,
        String feeCharged,
        String paidToDate,
        String buildingType,
        String deadLine,
        String architectPId,
        String contractorPId,
        String customerPId,
        String projectManagerPId,
        String structuralEngineerPId,
        // String completedDate,
        String isFinalised
    ) throws SQLException {
        String sqlStr = " SELECT"
            + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged,"
            + " PaidToDate, Deadline,"
            + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer,"
            + " Finalised, CompletedDate"
            + " FROM ProjectView WHERE "
            // + " AND ProjectNumber like '%"     + projectNoVal + "%'"
            +     " ProjectName like '%"       + projectName + "%'"
            + " AND BuildingType LIKE '%"      + buildingType + "%'"
            + " AND PhysicalAddress LIKE '%"   + physicalAddress + "%'"
            + " AND ERFNumber LIKE '%"         + eRFNumber + "%'" 
            + " AND FeeCharged LIKE '%"        + feeCharged + "%'"
            + " AND PaidToDate LIKE '%"        + paidToDate + "%'"
            + " AND Deadline LIKE '%"          + deadLine + "%'"
            + " AND Architect LIKE '%"         + architectPId + "%'" 
            + " AND Contractor LIKE '%"        + contractorPId + "%'"
            + " AND Customer LIKE '%"          + customerPId + "%'"
            + " AND ProjectManager LIKE '%"     + projectManagerPId + "%'"
            + " AND StructuralEngineer LIKE '%" + structuralEngineerPId + "%'"
            // + " AND CompletedDate LIKE '%"      + completedDate + "%'"
            + " AND Finalised LIKE '%"          + isFinalised + "%'";

        return makeProjectRow( sqlStr );
    };

    /** 
     * It is use at "Search By Project Number" Page. 
     * 
     * @param projectNo Project number.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> selectByProjectNumberRecord(String projectNo) throws SQLException {
        String sqlStr = " SELECT"
            + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged,"
            + " PaidToDate, Deadline,"
            + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer,"
            + " Finalised, CompletedDate"
            + " FROM ProjectView WHERE "
            + " ProjectNumber like '%" + projectNo + "%'";

        return this.makeProjectRow( sqlStr );
    }

    /** 
     * It is use at "Search By Project Name" Page. 
     * 
     * @param projectName Project's Name.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> selectByProjectNameRecord(String projectName) throws SQLException {
        String sqlStr = " SELECT"
            + " ProjectNumber, ProjectName, BuildingType, PhysicalAddress, ERFNumber, FeeCharged,"
            + " PaidToDate, Deadline,"
            + " Architect, Contractor, Customer, ProjectManager, StructuralEngineer,"
            + " Finalised, CompletedDate"
            + " FROM ProjectView WHERE "
            + " ProjectName like '%" + projectName + "%'";
        System.out.println("By Name: " + sqlStr);
        return this.makeProjectRow( sqlStr );
    }

    /** 
     * Select all Person record for Person JTable.  
     * 
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> selectPersonRecord() throws SQLException {
        return this.makePersonRow( this.selectPerson );
    }
    
    /** 
     * Select all Project record for Project JTable.  
     * 
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> selectProjectRecord() throws SQLException {
        return this.makeProjectRow( this.selectProject );
    }

    /** 
     * use in Finalised page. find any hasn't finalised record in Project table. 
     * 
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> selectNeedCompletedProjectRecord() throws SQLException {
        return this.makeProjectRow( this.selectNeedCompletedProject );
    }

    /**
     *  use in [Past Due Date] page.  
     * 
     * @param completedDate The Project's complete date.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> selectPastDueDate(String completedDate) throws SQLException {
        String sqlStr = this.selectProject 
                + " WHERE Deadline < '" + completedDate + "'"
                + " AND Finalised = 0";
        return this.makeProjectRow( sqlStr );  
    }
    
    /**
     * format Person Select Result to Vector -) Vector - String and 
     * for DefaultTableModel to display information to PersonTable ( JTable ).
     * 
     * @param sqlString follow this string to query database.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> makePersonRow(String sqlString) throws SQLException {
        Vector<Vector<String>> resultVector = new Vector<Vector<String>>();

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
            
        return resultVector;
    }

    /**
     * format Project Select Result to Vector -) Vector - String and 
     * for DefaultTableModel to display information to ProjectTable ( JTable ).
     * 
     * @param sqlString follow this string to query database.
     * @throws SQLException If the database cannot connection or query at database or table.
     * @return database query result and format for JTable 's Model.
     */
    public Vector<Vector<String>> makeProjectRow(String sqlString) throws SQLException {
        Vector<Vector<String>> resultVector = new Vector<Vector<String>>();

            ResultSet rs = this.statement.executeQuery( sqlString );
            while( rs.next() ){
                Vector<String> resultRow = new Vector<>();
                resultRow.add( Integer.toString( rs.getInt("ProjectNumber")) );
                resultRow.add( rs.getString("ProjectName") );
                resultRow.add( rs.getString("BuildingType") );
                resultRow.add( rs.getString("PhysicalAddress") );
                resultRow.add( rs.getString("ERFNumber") );
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
        return resultVector;
    }

    /** 
     * Update Project record's Finalised to '1' ( true ) and set the completeDate  
     * 
     * @param completedDate The Project's complete date.
     * @param projectNo Project number.
     * @throws SQLException If the database cannot connection or query at database or table.
     */
    public void finalisedProject(String completedDate, String projectNo) throws SQLException {
        String sqlStr = "UPDATE Project SET Finalised = 1, "
        + " CompletedDate = '" + completedDate + "'"
        + " Where ProjectNumber = '" + projectNo + "'" ;
        this.statement.executeUpdate(sqlStr);
    }

    /** 
     * Close sql statement and connection. 
     * 
     * @throws SQLException If the database cannot connection.
     */
    public void closeSQLConnectionAndStatement() {
        try {
            this.statement.close();
            this.connection.close();
        } catch (SQLException ce) {
            ce.printStackTrace();
        }
    }
    
}
