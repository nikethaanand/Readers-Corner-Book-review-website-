package reader.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reader.model.*;

public class BookCollectionDao {

	
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BookCollectionDao instance = null;
	protected BookCollectionDao() {
		connectionManager = new ConnectionManager();
	}
	public static BookCollectionDao getInstance() {
		if(instance == null) {
			instance = new BookCollectionDao();
		}
		return instance;
	}
	
	public BookCollections create(BookCollections bookcollection) throws SQLException {
		String insertbookcollection = "INSERT INTO BOOKCOLLECTIONS(collectionId,title) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertbookcollection,
					Statement.RETURN_GENERATED_KEYS);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, bookcollection.getCollection().getCollectionId());
			insertStmt.setString(2, bookcollection.getBook().getTitle());
			
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
			int bookcollectionId = -1;
			if(resultKey.next()) {
				bookcollectionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			bookcollection.setBookCollectionId(bookcollectionId);
			return bookcollection;
			
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
	public BookCollections getBookCollectionById(int bookcollectionId) throws SQLException {
		String selectbookcollection =
			"SELECT b.bookcollectionId, b.collectionId, c.collectionName, b.title " +
			"FROM BookCollections b INNER JOIN COLLECTIONS c ON b.collectionId = c.collectionId " +
			"WHERE b.bookcollectionId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectbookcollection);
			selectStmt.setInt(1, bookcollectionId);
			results = selectStmt.executeQuery();
			CollectionsDao collectionDao = CollectionsDao.getInstance();
			BooksDao bookDao = BooksDao.getInstance();
			if(results.next()) {
				int resultId = results.getInt("bookcollectionId");
				
				Collections collection = collectionDao.getCollectionById(results.getInt("collectionId"));
				Books book = bookDao.getBookByTitle(results.getString("title"));
				BookCollections BookCollection = new BookCollections(resultId, collection, book);
				return BookCollection;
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
	
	
	public List<Books> getBooksFromCollectionId(int collectionId)
			throws SQLException {
		List<Books> bookslist = new ArrayList<Books>();
		String selectBooks ="SELECT title "
				+ "FROM BOOKCOLLECTIONS WHERE collectionId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBooks);
			selectStmt.setInt(1, collectionId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String title1 = results.getString("title");
				
				Books book =
						new Books(title1);
				bookslist.add(book);


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
		return bookslist;
	}
	
	public BookCollections delete(BookCollections bookCollection) throws SQLException {
		String deleteBookCollection = "DELETE FROM BOOKCOLLECTIONS WHERE bookcollectionId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBookCollection);
			deleteStmt.setInt(1, bookCollection.getBookCollectionId());
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
