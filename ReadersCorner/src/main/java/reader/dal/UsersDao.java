package reader.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import reader.model.Users;


public class UsersDao {
    protected ConnectionManager connectionManager;
   private static UsersDao instance = null;
   protected UsersDao() {
      connectionManager = new ConnectionManager();
   }
   public static UsersDao getInstance() {
      if(instance == null) {
         instance = new UsersDao();
      }
      return instance;
   }
    // CREATE TABLE USERS(
    //     userId int NOT NULL auto_increment,
    //     userName VARCHAR(255),
    //     password VARCHAR(255),
    //     CONSTRAINT pk_USERS_userId PRIMARY KEY(userId),
    //     CONSTRAINT uk_USERS_userId UNIQUE KEY(userId, userName)
    //     );
            
   public Users create(Users user) throws SQLException {
      String insertUser = "INSERT INTO USERS(userName,Password) VALUES(?,?);";
      Connection connection = null;
      PreparedStatement insertStmt = null;
      try {
         connection = connectionManager.getConnection();
         insertStmt = connection.prepareStatement(insertUser);
         // PreparedStatement allows us to substitute specific types into the query template.
         
         // insertStmt.setString(1, user.getUserId());
         insertStmt.setString(1, user.getUserName());
         insertStmt.setString(2, user.getPassword());
         
         insertStmt.executeUpdate();
         
         return user;
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      } finally {
         if(connection != null) {
            connection.close();
         }
         if(insertStmt != null) {
            insertStmt.close();
         }
      }
   }
   
   public Users delete(Users user) throws SQLException {
      String deleteUser = "DELETE FROM USERS WHERE userName=?;";
      Connection connection = null;
      PreparedStatement deleteStmt = null;
      try {
         connection = connectionManager.getConnection();
         deleteStmt = connection.prepareStatement(deleteUser);
         deleteStmt.setString(1, user.getUserName());
         deleteStmt.executeUpdate();
         // Return null so the caller can no longer operate on the Persons instance.
         return null;
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      } finally {
         if(connection != null) {
            connection.close();
         }
         if(deleteStmt != null) {
            deleteStmt.close();
         }
      }
   }
   
   public Users getUserByUserName(String username) throws SQLException {
      String selectPerson = "SELECT userName, password FROM USERS WHERE username=?;";
      Connection connection = null;
      PreparedStatement selectStmt = null;
      ResultSet results = null;
      try {
         connection = connectionManager.getConnection();
         selectStmt = connection.prepareStatement(selectPerson);
         selectStmt.setString(1, username);
         
         results = selectStmt.executeQuery();
      
         if(results.next()) {
         
            String resultUserName = results.getString("username");
            //String password1=results.getString("password");
            Users user=new Users(resultUserName);
            return user;
         }
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      } finally {
         if(connection != null) {
            connection.close();
         }
         if(selectStmt != null) {
            selectStmt.close();
         }
         if(results != null) {
            results.close();
         }
      }
      return null;
   }
   public Users updatePassword(Users user, String password) throws SQLException {
	   
      String updatePassword = "UPDATE BookReview.USERS SET BookReview.USERS.password =? WHERE BookReview.USERS.userName =?;";
  
      Connection connection = null;
      PreparedStatement updateStmt = null;
      try {
         connection = connectionManager.getConnection();
         updateStmt = connection.prepareStatement(updatePassword);
         System.out.println("pw:==== "+ password);
         updateStmt.setString(1, password);
         updateStmt.setString(2, user.getUserName());
         updateStmt.executeUpdate();
         user.setPassword(password);
         return user;
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      } finally {
         if(connection != null) {
            connection.close();
         }
         if(updateStmt != null) {
            updateStmt.close();
         }
      }
   }

      
}
