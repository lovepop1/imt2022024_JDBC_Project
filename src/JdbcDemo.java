//STEP 1. Import required packages

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo {

   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/infin8";

   // Database credentials
   static final String USER = "root";// add your user
   static final String PASSWORD = "admin";// add password

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;

      
      // STEP 2. Connecting to the Database
      try {
         // STEP 2a: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // STEP 2b: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         conn.setAutoCommit(false);
         // STEP 2c: Execute a query
         System.out.println("Creating statement...");
         stmt = conn.createStatement();

         // INSERT, UPDATE, DELETE
         

         // STEP 3: Query to database
         Scanner scanner = new Scanner(System.in);
         boolean exit = false;
 
         while (!exit) {
             System.out.println("Select an option:");
             System.out.println("1. View all events");
             System.out.println("2. Add a new event");
             System.out.println("3. Update event details");
             System.out.println("4. Delete an event");
             System.out.println("5. View all participants");
             System.out.println("6. Add a new participant");
             System.out.println("7. Update participant details");
             System.out.println("8. Delete a participant");
             System.out.println("9. Register participant for an event");
             System.out.println("10. View all registrations");
             System.out.println("11. Add a new organizer");
             System.out.println("12. View all organizers");
             System.out.println("13. Update organizer details");
             System.out.println("14. Delete an organizer");
             System.out.println("15. Add a new sponsor");
             System.out.println("16. View all sponsors");
             System.out.println("17. Update sponsor details");
             System.out.println("18. Delete a sponsor");
             System.out.println("19. Search for an event by name");
             System.out.println("20. Search for a participant by name");
             System.out.println("21. Search for an organizer by name");
             System.out.println("22. Display SPOCS of events");
             System.out.println("23. Insert + Update on registrations");
             System.out.println("24. Exit");
 
             int option = scanner.nextInt();
             scanner.nextLine(); // Consume newline
 
             switch (option) {
                 case 1:
                     viewAllEvents(stmt);
                     break;
                 case 2:
                     addEvent(stmt,scanner,conn);
                     break;
                 case 3:
                     updateEventDetails(stmt,scanner,conn);
                     break;
                 case 4:
                     deleteEvent(stmt,scanner,conn);
                     break;
                 case 5:
                     viewAllParticipants(stmt);
                     break;
                 case 6:
                     addParticipant(stmt,scanner,conn);
                     break;
                 case 7:
                     updateParticipantDetails(stmt,scanner,conn);
                     break;
                 case 8:
                     deleteParticipant(stmt,scanner,conn);
                     break;
                 case 9:
                     registerParticipantForEvent(stmt,scanner,conn);
                     break;
                 case 10:
                     viewAllRegistrations(stmt);
                     break;
                 case 11:
                     addOrganizer(stmt,scanner,conn);
                     break;
                 case 12:
                     viewAllOrganizers(stmt);
                     break;
                 case 13:
                     updateOrganizerDetails(stmt,scanner,conn);
                     break;
                 case 14:
                     deleteOrganizer(stmt,scanner,conn);
                     break;
                 case 15:
                     addSponsor(stmt,scanner,conn);
                     break;
                 case 16:
                     viewAllSponsors(stmt);
                     break;
                 case 17:
                     updateSponsorDetails(stmt,scanner,conn);
                     break;
                 case 18:
                     deleteSponsor(stmt,scanner,conn);
                     break;
                 case 19:
                     searchEventByName(stmt,scanner);
                     break;
                 case 20:
                     searchParticipantByName(stmt,scanner);
                     break;
                 case 21:
                     searchOrganizerByName(stmt,scanner);
                     break;
                 case 22:
                     eventsofspoc(stmt,scanner);
                     break;
                 case 23:
                     insert_updatereg(stmt,scanner,conn);
                     break;
                 case 24:
                     exit = true;
                     break;
                 default:
                     System.out.println("Invalid option. Please try again.");
             }
         }
 
        scanner.close();
         // STEP 5: Clean-up environment
         stmt.close();
         conn.close();
      } catch (SQLException se) { // Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) { // Handle errors for Class.forName
         e.printStackTrace();
      } finally { // finally block used to close resources regardless of whether an exception was
                  // thrown or not
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         } // end finally try
      } // end try
      System.out.println("End of Code");
   } // end main

// Note : By default autocommit is on. you can set to false using
// con.setAutoCommit(false)
private static void viewAllEvents(Statement stmt) throws SQLException {
    String selectQuery = "SELECT * FROM Event";
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
        int eventID = rs.getInt("EventID");
        String eventName = rs.getString("EventName");
        String eventType = rs.getString("EventType");
        String startDate = rs.getString("EventStartDate");
        String endDate = rs.getString("EventEndDate");
        String description = rs.getString("EventDescription");
        System.out.println("Event ID: " + eventID + ", Name: " + eventName +
                ", Type: " + eventType + ", Start Date: " + startDate +
                ", End Date: " + endDate + ", Description: " + description);
    }
    rs.close();
}
private static void addEvent(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter event details:");
        System.out.print("Event ID: ");
        int eventID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Type: ");
        String eventType = scanner.nextLine();
        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        String insertQuery = "INSERT INTO Event (EventID, EventName, EventType, EventStartDate, EventEndDate, EventDescription) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = stmt.getConnection().prepareStatement(insertQuery);
        preparedStatement.setInt(1, eventID);
        preparedStatement.setString(2, eventName);
        preparedStatement.setString(3, eventType);
        preparedStatement.setString(4, startDate);
        preparedStatement.setString(5, endDate);
        preparedStatement.setString(6, description);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Event inserted successfully...");
            conn.commit();
        }
        else{
            conn.rollback();
        }
    }
    catch (SQLException e){
        System.out.println(e.getMessage());
    }
     finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void updateEventDetails(Statement stmt,Scanner scanner,Connection conn) throws SQLException {
    System.out.println("Enter event name to update:");
    String eventNameToUpdate = scanner.nextLine();
    System.out.println("Enter new event description:");
    String newDescription = scanner.nextLine();

    String updateQuery = "UPDATE Event SET EventDescription = '" + newDescription + "' WHERE EventName = '" + eventNameToUpdate + "'";
    int rowsUpdated = stmt.executeUpdate(updateQuery);
    if (rowsUpdated > 0) {
        System.out.println("Event description updated successfully...");
        conn.commit();
    } else {
        System.out.println("Event not found or description unchanged...");
        conn.rollback();
    }
}
private static void deleteEvent(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter event ID to delete:");
        int eventIDToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String deleteQuery = "DELETE FROM Event WHERE EventID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(deleteQuery);
        preparedStatement.setInt(1, eventIDToDelete);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Event deleted successfully...");
            conn.commit();
        } else {
            System.out.println("Event not found...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
     finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

private static void viewAllParticipants(Statement stmt) throws SQLException {
    String selectQuery = "SELECT * FROM Participant";
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
        int participantID = rs.getInt("ParticipantID");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String email = rs.getString("Email");
        String phone = rs.getString("Phone");
        System.out.println("Participant ID: " + participantID + ", Name: " + firstName + " " + lastName +
                ", Email: " + email + ", Phone: " + phone);
    }
    rs.close();
}
private static void addParticipant(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter participant details:");
        System.out.print("Participant ID: ");
        int participantID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        String insertQuery = "INSERT INTO Participant (ParticipantID, FirstName, LastName, Email, Phone) " +
                "VALUES (?, ?, ?, ?, ?)";
        preparedStatement = stmt.getConnection().prepareStatement(insertQuery);
        preparedStatement.setInt(1, participantID);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, phone);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Participant inserted successfully...");
            conn.commit();
        }
        else{
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

private static void updateParticipantDetails(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter participant ID to update:");
        int participantIDToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new first name:");
        String newFirstName = scanner.nextLine();
        System.out.println("Enter new last name:");
        String newLastName = scanner.nextLine();
        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        System.out.println("Enter new phone:");
        String newPhone = scanner.nextLine();

        String updateQuery = "UPDATE Participant SET FirstName = ?, LastName = ?, Email = ?, Phone = ? WHERE ParticipantID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(updateQuery);
        preparedStatement.setString(1, newFirstName);
        preparedStatement.setString(2, newLastName);
        preparedStatement.setString(3, newEmail);
        preparedStatement.setString(4, newPhone);
        preparedStatement.setInt(5, participantIDToUpdate);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Participant details updated successfully...");
            conn.commit();
        } else {
            System.out.println("Participant not found...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void deleteParticipant(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter participant ID to delete:");
        int participantIDToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String deleteQuery = "DELETE FROM Participant WHERE ParticipantID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(deleteQuery);
        preparedStatement.setInt(1, participantIDToDelete);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Participant deleted successfully...");
            conn.commit();
        } else {
            System.out.println("Participant not found...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

private static void registerParticipantForEvent(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter registration ID:");
        int registrationID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter participant ID to register:");
        int participantID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter event ID to register for:");
        int eventID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter registration date (YYYY-MM-DD):");
        String registrationDate = scanner.nextLine();

        String insertQuery = "INSERT INTO Registration (RegistrationID, ParticipantID, EventID, RegistrationDate) " +
                "VALUES (?, ?, ?, ?)";
        preparedStatement = stmt.getConnection().prepareStatement(insertQuery);
        preparedStatement.setInt(1, registrationID);
        preparedStatement.setInt(2, participantID);
        preparedStatement.setInt(3, eventID);
        preparedStatement.setString(4, registrationDate);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Participant registered for event successfully...");
            conn.commit();
        } else {
            System.out.println("Failed to register participant for event...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void insert_updatereg(Statement stmt,Scanner scanner,Connection conn) throws SQLException{
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    try {
        System.out.println("Enter registration ID:");
        int registrationID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter participant ID to register:");
        int participantID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter event ID to register for:");
        int eventID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter registration date (YYYY-MM-DD):");
        String registrationDate = scanner.nextLine();

        String insertQuery = "INSERT INTO Registration (RegistrationID, ParticipantID, EventID, RegistrationDate) " +
                "VALUES (?, ?, ?, ?)";
        preparedStatement = stmt.getConnection().prepareStatement(insertQuery);
        preparedStatement.setInt(1, registrationID);
        preparedStatement.setInt(2, participantID);
        preparedStatement.setInt(3, eventID);
        preparedStatement.setString(4, registrationDate);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Participant registered for event successfully...");
        } else {
            System.out.println("Failed to register participant for event...");
        }
        System.out.println("Enter registration ID to update:");
        int registrationIDToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter participant ID to update:");
        int participantIDToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter event ID to update:");
        int eventIDToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter registration date (YYYY-MM-DD):");
        String registrationDatenew = scanner.nextLine();

        String updateQuery = "update Registration set EventID = ? , ParticipantID = ?, RegistrationDate = ? where RegistrationID= ? ";
        preparedStatement2 = stmt.getConnection().prepareStatement(updateQuery);
        preparedStatement2.setInt(1, eventIDToUpdate);
        preparedStatement2.setInt(2, participantIDToUpdate);
        preparedStatement2.setString(3, registrationDatenew);
        preparedStatement2.setInt(4, registrationIDToUpdate);

        int rowsUpdated2 = preparedStatement2.executeUpdate();
        if (rowsUpdated2 > 0) {
            conn.commit();
            System.out.println("Participant registered for event successfully...");
        } else {
            System.out.println("Failed to register participant for event...");
        }
    }
    catch(SQLException e1){
        e1.printStackTrace();
      // If there is an error then rollback the changes.
      System.out.println("Rolling back data here....");
      try{
         if(conn!=null)
             conn.rollback();
      }catch(SQLException se2){
	      System.out.println("Rollback failed....");
              se2.printStackTrace();
      }
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

}
private static void viewAllRegistrations(Statement stmt) throws SQLException {
    String selectQuery = "SELECT * FROM Registration";
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
        int registrationID = rs.getInt("RegistrationID");
        int eventID = rs.getInt("EventID");
        int participantID = rs.getInt("ParticipantID");
        String registrationDate = rs.getString("RegistrationDate");
        System.out.println("Registration ID: " + registrationID + ", Event ID: " + eventID +
                ", Participant ID: " + participantID + ", Registration Date: " + registrationDate);
    }
    rs.close();
}
private static void addOrganizer(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter organizer details:");
        System.out.print("Organizer ID: ");
        int organizerID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Position: ");
        String position = scanner.nextLine();
        System.out.print("Event Name: ");
        String eventName = scanner.nextLine();

        String insertQuery = "INSERT INTO Organizer (OrganizerID, FirstName, LastName, Position, EventName) " +
                             "VALUES (?, ?, ?, ?, ?)";
        preparedStatement = stmt.getConnection().prepareStatement(insertQuery);
        preparedStatement.setInt(1, organizerID);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, position);
        preparedStatement.setString(5, eventName);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Organizer inserted successfully...");
            conn.commit();
        } else {
            System.out.println("Failed to insert organizer...");
            conn.rollback();
        }
    } 
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void viewAllOrganizers(Statement stmt) throws SQLException {
    String selectQuery = "SELECT * FROM Organizer";
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
        int organizerID = rs.getInt("OrganizerID");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String position = rs.getString("Position");
        String eventName = rs.getString("EventName");
        System.out.println("Organizer ID: " + organizerID + ", Name: " + firstName + " " + lastName +
                ", Position: " + position + ", Event Name: " + eventName);
    }
    rs.close();
}
private static void updateOrganizerDetails(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter organizer ID to update:");
        int organizerIDToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new first name:");
        String newFirstName = scanner.nextLine();
        System.out.println("Enter new last name:");
        String newLastName = scanner.nextLine();
        System.out.println("Enter new position:");
        String newPosition = scanner.nextLine();
        System.out.println("Enter new event name:");
        String newEventName = scanner.nextLine();

        String updateQuery = "UPDATE Organizer SET FirstName = ?, LastName = ?, Position = ?, EventName = ? WHERE OrganizerID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(updateQuery);
        preparedStatement.setString(1, newFirstName);
        preparedStatement.setString(2, newLastName);
        preparedStatement.setString(3, newPosition);
        preparedStatement.setString(4, newEventName);
        preparedStatement.setInt(5, organizerIDToUpdate);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Organizer details updated successfully...");
            conn.commit();
        } else {
            System.out.println("Organizer not found...");
            conn.rollback();
        }
    } 
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void deleteOrganizer(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter organizer ID to delete:");
        int organizerIDToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String deleteQuery = "DELETE FROM Organizer WHERE OrganizerID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(deleteQuery);
        preparedStatement.setInt(1, organizerIDToDelete);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Organizer deleted successfully...");
            conn.commit();
        } else {
            System.out.println("Organizer not found...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void addSponsor(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter sponsor details:");
        System.out.print("Sponsor ID: ");
        int sponsorID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Sponsor Name: ");
        String sponsorName = scanner.nextLine();
        System.out.print("Sponsor Type: ");
        String sponsorType = scanner.nextLine();
        System.out.print("Contact Person: ");
        String contactPerson = scanner.nextLine();
        System.out.print("Contact Email: ");
        String contactEmail = scanner.nextLine();
        System.out.print("Contact Phone: ");
        String contactPhone = scanner.nextLine();

        String insertQuery = "INSERT INTO Sponsor (SponsorID, SponsorName, SponsorType, ContactPerson, ContactEmail, ContactPhone) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = stmt.getConnection().prepareStatement(insertQuery);
        preparedStatement.setInt(1, sponsorID);
        preparedStatement.setString(2, sponsorName);
        preparedStatement.setString(3, sponsorType);
        preparedStatement.setString(4, contactPerson);
        preparedStatement.setString(5, contactEmail);
        preparedStatement.setString(6, contactPhone);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Sponsor inserted successfully...");
            conn.commit();
        } else {
            System.out.println("Failed to insert sponsor...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void viewAllSponsors(Statement stmt) throws SQLException {
    String selectQuery = "SELECT * FROM Sponsor";
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
        int sponsorID = rs.getInt("SponsorID");
        String sponsorName = rs.getString("SponsorName");
        String sponsorType = rs.getString("SponsorType");
        String contactPerson = rs.getString("ContactPerson");
        String contactEmail = rs.getString("ContactEmail");
        String contactPhone = rs.getString("ContactPhone");
        System.out.println("Sponsor ID: " + sponsorID + ", Name: " + sponsorName +
                ", Type: " + sponsorType + ", Contact Person: " + contactPerson +
                ", Contact Email: " + contactEmail + ", Contact Phone: " + contactPhone);
    }
    rs.close();
}
private static void updateSponsorDetails(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter sponsor ID to update:");
        int sponsorIDToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new sponsor name:");
        String newSponsorName = scanner.nextLine();
        System.out.println("Enter new sponsor type:");
        String newSponsorType = scanner.nextLine();
        System.out.println("Enter new contact person:");
        String newContactPerson = scanner.nextLine();
        System.out.println("Enter new contact email:");
        String newContactEmail = scanner.nextLine();
        System.out.println("Enter new contact phone:");
        String newContactPhone = scanner.nextLine();

        String updateQuery = "UPDATE Sponsor SET SponsorName = ?, SponsorType = ?, ContactPerson = ?, ContactEmail = ?, ContactPhone = ? WHERE SponsorID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(updateQuery);
        preparedStatement.setString(1, newSponsorName);
        preparedStatement.setString(2, newSponsorType);
        preparedStatement.setString(3, newContactPerson);
        preparedStatement.setString(4, newContactEmail);
        preparedStatement.setString(5, newContactPhone);
        preparedStatement.setInt(6, sponsorIDToUpdate);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Sponsor details updated successfully...");
            conn.commit();
        } else {
            System.out.println("Sponsor not found...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void deleteSponsor(Statement stmt, Scanner scanner,Connection conn) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        System.out.println("Enter sponsor ID to delete:");
        int sponsorIDToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String deleteQuery = "DELETE FROM Sponsor WHERE SponsorID = ?";
        preparedStatement = stmt.getConnection().prepareStatement(deleteQuery);
        preparedStatement.setInt(1, sponsorIDToDelete);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Sponsor deleted successfully...");
            conn.commit();
        } else {
            System.out.println("Sponsor not found...");
            conn.rollback();
        }
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    } 
    finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
private static void searchEventByName(Statement stmt,Scanner scanner) throws SQLException {
    System.out.println("Enter the name of the event to search:");
    String eventName = scanner.nextLine();

    String selectQuery = "SELECT * FROM Event WHERE EventName = '" + eventName + "'";
    ResultSet rs = stmt.executeQuery(selectQuery);
    if (rs.next()) {
        int eventID = rs.getInt("EventID");
        String eventType = rs.getString("EventType");
        String startDate = rs.getString("EventStartDate");
        String endDate = rs.getString("EventEndDate");
        String description = rs.getString("EventDescription");
        System.out.println("Event found:");
        System.out.println("Event ID: " + eventID);
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Type: " + eventType);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Description: " + description);
    } else {
        System.out.println("Event not found...");
    }
    rs.close();
}
private static void searchParticipantByName(Statement stmt,Scanner scanner) throws SQLException {
    System.out.println("Enter the name of the participant to search:");
    String participantName = scanner.nextLine();

    String selectQuery = "SELECT * FROM Participant WHERE FirstName = '" + participantName + "' OR LastName = '" + participantName + "'";
    ResultSet rs = stmt.executeQuery(selectQuery);
    if (rs.next()) {
        int participantID = rs.getInt("ParticipantID");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String email = rs.getString("Email");
        String phone = rs.getString("Phone");
        System.out.println("Participant found:");
        System.out.println("Participant ID: " + participantID);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    } else {
        System.out.println("Participant not found...");
    }
    rs.close();
}
private static void searchOrganizerByName(Statement stmt,Scanner scanner) throws SQLException {
    System.out.println("Enter the name of the organizer to search:");
    String organizerName = scanner.nextLine();

    String selectQuery = "SELECT * FROM Organizer WHERE FirstName = '" + organizerName + "' OR LastName = '" + organizerName + "'";
    ResultSet rs = stmt.executeQuery(selectQuery);
    if (rs.next()) {
        int organizerID = rs.getInt("OrganizerID");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String position = rs.getString("Position");
        String eventName = rs.getString("EventName");
        System.out.println("Organizer found:");
        System.out.println("Organizer ID: " + organizerID);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Position: " + position);
        System.out.println("Event Name: " + eventName);
    } else {
        System.out.println("Organizer not found...");
    }
    rs.close();
}
private static void eventsofspoc(Statement stmt, Scanner scanner) throws SQLException {
    System.out.println("Events managed by SPOCs:");

    String selectQuery = "SELECT o.OrganizerID, o.FirstName, o.LastName, o.Position, e.EventID, e.EventName, e.EventType, e.EventStartDate, e.EventEndDate, e.EventDescription " +
                         "FROM Organizer o " +
                         "JOIN Event e ON o.EventName = e.EventName " +
                         "WHERE o.Position = 'SPOC'";
    
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
        int organizerID = rs.getInt("OrganizerID");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String position = rs.getString("Position");
        int eventID = rs.getInt("EventID");
        String eventName = rs.getString("EventName");
        String eventType = rs.getString("EventType");
        String startDate = rs.getString("EventStartDate");
        String endDate = rs.getString("EventEndDate");
        String description = rs.getString("EventDescription");
        
        System.out.println("Organizer ID: " + organizerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Position: " + position);
        System.out.println("Event ID: " + eventID);
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Type: " + eventType);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Description: " + description);
        System.out.println();
    }
    rs.close();
}

}
