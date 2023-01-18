package reader.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import reader.model.*;

public class PublishersDao {
    protected ConnectionManager connectionManager;


    private static PublishersDao instance = null;
    protected PublishersDao() {
        connectionManager = new ConnectionManager();
    }
    public static PublishersDao getInstance() {
        if (instance == null) {
            instance = new PublishersDao();
        }
        return instance;
    }
    
//    CREATE TABLE PUBLISHERS(
//    		userName VARCHAR(255),
//    		lastLogin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    		CONSTRAINT pk_PUBLISHERS_userName PRIMARY KEY(userName),
//    		CONSTRAINT fk_PUBLISHERS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
//    		ON UPDATE CASCADE ON DELETE CASCADE
//    		);
//    
    public Publishers create(Publishers publisher) throws SQLException {
        String insertPublisher = "INSERT INTO PUBLISHERS(userName,lastLogin) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            //insertStmt = connection.prepareStatement(insertUser);
            insertStmt = connection.prepareStatement(insertPublisher);

            insertStmt.setString(1, publisher.getUserName());
            // Change to getId to lastLogin to match with attributes in original SQL - VY
            insertStmt.setString(2, publisher.getPassword());
        	insertStmt.setTimestamp(3, new Timestamp(publisher.getLastLogin().getTime()));
            insertStmt.executeUpdate();
            return publisher;
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }
    
    public Publishers delete(Publishers publisher) throws SQLException {
        String deletePublisher = "DELETE FROM PUBLISHERS WHERE userName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePublisher);
            deleteStmt.setString(1, publisher.getUserName());
            deleteStmt.executeUpdate();

            return null;
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
    
    
    
    public Publishers getPublisherByUserName(String username) throws SQLException {
        String selectPerson = "SELECT userName,lastLogin FROM PUBLISHERS WHERE userName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setString(1, username);

            results = selectStmt.executeQuery();
           

            if (results.next()) {

                String resultUserName = results.getString("userName");
          
                // I change userId to lastLogin to match with attributes from original SQL -- Vy
                Publishers publisher = new Publishers(resultUserName);

                return publisher;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }
    
   
    
}