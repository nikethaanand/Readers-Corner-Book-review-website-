package reader.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import reader.model.*;

public class ReadlistDao {

	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReadlistDao instance = null;
	protected ReadlistDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReadlistDao getInstance() {
		if(instance == null) {
			instance = new ReadlistDao();
		}
		return instance;
	}
	
	public Readlist create(Readlist readlist) throws SQLException {
		String insertReadlist = "INSERT INTO Readlist(userName,title) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReadlist,
					Statement.RETURN_GENERATED_KEYS);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, readlist.getUser().getUserName());
			insertStmt.setString(2, readlist.getBook().getTitle());
			
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
			int readlistId = -1;
			if(resultKey.next()) {
				readlistId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			readlist.setReadListId(readlistId);
			return readlist;
			
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
	
	
	public List<Readlist> getReadlistByuserName(String userName)
			throws SQLException {
		List<Readlist> readlists = new ArrayList<Readlist>();
		String selectReadlist = "SELECT readlistid, username, title FROM READLIST "
				+ "WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReadlist);
			selectStmt.setString(1,userName);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			BooksDao bookDao = BooksDao.getInstance();
			while(results.next()) {
				int resultreadListId = results.getInt("readListId");
				
				Users user = userDao.getUserByUserName(results.getString("userName"));
				Books book = bookDao.getBookByTitle(results.getString("title"));
				
				Readlist readlist = new Readlist(resultreadListId, user, book);
				readlists.add(readlist);
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
		return readlists;
	}
	
	
	public Readlist getReadlistById(int readListId) throws  SQLException {

		String selectReadlist =
				"SELECT readListId, userName, title FROM READLIST " +
				"WHERE readListId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReadlist);
			selectStmt.setInt(1, readListId);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			BooksDao bookDao = BooksDao.getInstance();
			if(results.next()) {
				int readListId1 = results.getInt("readListId");

     		Users user = userDao.getUserByUserName(results.getString("userName"));
			Books book = bookDao.getBookByTitle(results.getString("title"));
				
				Readlist readlist = new Readlist(readListId, user, book);
//				Books book =
//						new Books(readListId, description,author,publisher,categories,publishedDate,ratingCount);
				return readlist;
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
	
	
	

	
	public Readlist delete(Readlist readlist) throws SQLException {
		String deleteReadlist = "DELETE FROM READLIST WHERE readListId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReadlist);
			deleteStmt.setInt(1, readlist.getReadListId());
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
