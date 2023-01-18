package reader.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import reader.model.*;

public class AdminsDao {
	protected ConnectionManager connectionManager;
	
	
	private static AdminsDao instance = null;
	protected AdminsDao() {
		connectionManager = new ConnectionManager();
	}
	public static AdminsDao getInstance() {
		if(instance == null) {
			instance = new AdminsDao();
		}
		return instance;
	}

	
//	CREATE TABLE ADMINS(
//			userName VARCHAR(255),
//			createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//			CONSTRAINT pk_ADMINS_userName PRIMARY KEY(userName),
//			CONSTRAINT fk_ADMINS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
//			);

// public Admins(String userName, String password, Timestamp createdDate) {
// 	super(userName, password);
// 	this.createdDate = createdDate;
// }


	public Admins create(Admins admin) throws SQLException {
		//String insertAdmin = "INSERT INTO ADMINS(userName,userId) VALUES(?,?);";
		String insertAdmin = "INSERT INTO ADMINS(userName,password,createdDate) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdmin);
		
			insertStmt.setString(1, admin.getUserName());
			insertStmt.setString(2, admin.getPassword());
			//insertStmt.setString(2, admins.getUserId());
			// Changing ID-> createdDate to match w/ attribute in original SQL -- Vy 
			// insertStmt.setTimestamp(3, new Timestamp(admin.getCreatedDate().getTime()));
			insertStmt.setTimestamp(3, admin.getCreatedDate());
			insertStmt.executeUpdate();
			return admin;
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

	
	
	public Admins delete(Admins admin) throws SQLException {
		//String deleteAdmin = "DELETE FROM ADMINS WHERE username=?;";
		String deleteAdmin = "DELETE FROM ADMINS WHERE userName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdmin);
			deleteStmt.setString(1, admin.getUserName());
			deleteStmt.executeUpdate();

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


	//	CREATE TABLE ADMINS(
	//	userName VARCHAR(255),
	//	createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	//	CONSTRAINT pk_ADMINS_userName PRIMARY KEY(userName),
	//	CONSTRAINT fk_ADMINS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
	//	);
	public Admins getAdminByAdminName(String username) throws SQLException {
		//String selectPerson = "SELECT userName,userId FROM ADMINS WHERE userName=?;";
		String selectAdmin = "SELECT userName,password,createdDate FROM ADMINS WHERE userName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			//selectStmt = connection.prepareStatement(selectPerson);
			selectStmt = connection.prepareStatement(selectAdmin);
			//selectStmt.setString(1, userName);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				String resultUserName = results.getString("userName");
				String password1 = results.getString("password");
				
				//String userId1=results.getString("userId");
				// I change userId to createdDate to match with attributes from original SQL -- Vy
				Timestamp createdDate = new Timestamp(results.getTimestamp("createdDate").getTime());
				//Users admin =new Admins(resultUserName,userId1);
				Admins admin =new Admins(resultUserName,password1, createdDate);

				return admin;
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
}