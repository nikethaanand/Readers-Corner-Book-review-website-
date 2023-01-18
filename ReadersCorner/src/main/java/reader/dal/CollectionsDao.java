package reader.dal;
import reader.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


public class CollectionsDao {

	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CollectionsDao instance = null;
	protected CollectionsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CollectionsDao getInstance() {
		if(instance == null) {
			instance = new CollectionsDao();
		}
		return instance;
	}
	
	
	
	public Collections create(Collections collection) throws SQLException {
		String insertCollection = "INSERT INTO Collections(collectionName,userName) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCollection,
					Statement.RETURN_GENERATED_KEYS);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, collection.getCollectionName());
			insertStmt.setString(2, collection.getUser().getUserName());
			//insertStmt.setString(2, collection.getUser());
			
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			resultKey = insertStmt.getGeneratedKeys();
			int collectionId = -1;
			if(resultKey.next()) {
				collectionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			collection.setCollectionId(collectionId);
			return collection;
			
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
	
	public Collections getCollectionById(int collectionId) throws SQLException {
		String selectcollection =
			"SELECT * from COLLECTIONS " +
			"WHERE collectionId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection(); 
			selectStmt = connection.prepareStatement(selectcollection);
			selectStmt.setInt(1, collectionId);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			if(results.next()) {
				int resultId = results.getInt("collectionId");
				String CollectionName = results.getString("collectionName");
				Users user = userDao.getUserByUserName(results.getString("userName"));
				
				Collections collection = new Collections(resultId, CollectionName, user);
				return collection;
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
	
	public List<Collections>getCollectionsByUserName(String userName) throws SQLException {
		List<Collections> Collections = new ArrayList<Collections>();
		String selectCollection =
			"SELECT collectionId, collectionName, userName from Collections " +
			"WHERE userName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCollection);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao UserDao = UsersDao.getInstance();
			
			while(results.next()) {
				int resultCollectionId = results.getInt("collectionId");
				String collectionName = results.getString("collectionName");
				Users user = UserDao.getUserByUserName(results.getString("userName"));
						//etCompanyByCompanyName(results.getString("companyName"));
				
				Collections Collection = new Collections(resultCollectionId,collectionName, user);
				Collections.add(Collection);
				
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
		return Collections;
	}
	
	
	public Collections updateCollectionName(Collections collection, String newCollectionName) throws SQLException {
		String updateCollection = "UPDATE Collections SET collectionName=? WHERE collectionId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCollection);
			updateStmt.setString(1, newCollectionName);
			updateStmt.setInt(2, collection.getCollectionId());
			
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			collection.setCollectionName(newCollectionName);
			
			return collection;
			
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
	
	public Collections delete(Collections collection) throws SQLException {
		String deleteCollection = "DELETE FROM Collections WHERE collectionId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCollection);
			deleteStmt.setInt(1, collection.getCollectionId());
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
	
	
}
