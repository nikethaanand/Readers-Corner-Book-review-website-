package reader.dal;
import reader.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BooksDao {
	protected ConnectionManager connectionManager;
	
	
	private static BooksDao instance = null;
	protected BooksDao() {
		connectionManager = new ConnectionManager();
	}
	public static BooksDao getInstance() {
		if(instance == null) {
			instance = new BooksDao();
		}
		return instance;
	}
	
	public Books create(Books book) throws SQLException {
		String insertBook = 
				"INSERT INTO BOOKS(title,description,authors,categories,publishedDate,ratingsCount) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBook);
		
			insertStmt.setString(1, book.getTitle());
			insertStmt.setString(2, book.getDescription());
			insertStmt.setString(3, book.getAuthors());	
//			insertStmt.setString(4, book.getPublisher().getUserName());
			insertStmt.setString(4, book.getCategories());
			insertStmt.setDate(5, book.getPublishedDate());
			insertStmt.setInt(6, book.getRatingsCount());
	
			insertStmt.executeUpdate();
				
			return book;
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
	
	
	/** DELETE */
	public Books delete(Books book) throws SQLException {
		String deleteBook = "DELETE FROM BOOKS WHERE title=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBook);
			deleteStmt.setString(1, book.getTitle());
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
	
	/** READ */
	/** get book by title */
	public Books getBookByTitle(String title) throws SQLException {
		String selectBook = 
				"SELECT title,description,authors, publisher,categories,publishedDate,ratingsCount "
				+ "FROM BOOKS WHERE title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			PublishersDao publishersDao =  PublishersDao.getInstance();
			if(results.next()) {
				String resultTitle = results.getString("title");
				String description = results.getString("description");
				String author = results.getString("authors");
				Publishers publisher = publishersDao.getPublisherByUserName("publisher");
				String categories = results.getString("categories");
				Date publishedDate = results.getDate("publishedDate");
				int ratingCount = results.getInt("ratingsCount");
				
				Books book =
						new Books(resultTitle, description,author,publisher,categories,publishedDate,ratingCount);
				return book;
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
	
	/** get book by author */
	public Books getBookByAuthor(String author) throws SQLException {
		String selectBook = 
				"SELECT title,description,authors, publisher,categories,publishedDate,ratingCount "
				+ "FROM BOOKS WHERE author=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, author);
			results = selectStmt.executeQuery();
			PublishersDao publishersDao =  PublishersDao.getInstance();
			if(results.next()) {
				String title = results.getString("title");
				String description = results.getString("description");
				String resultAuthor = results.getString("authors");
				Publishers publisher = publishersDao.getPublisherByUserName("publisher");
				String categories = results.getString("categories");
				Date publishedDate = results.getDate("publishedDate");
				int ratingCount = results.getInt("ratingCount");
				
				Books book =
						new Books(title, description,resultAuthor,publisher,categories,publishedDate,ratingCount);
				return book;
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
	
	
	/** UPDATE ratingsCount
	 * This runs a UPDATE statement.
	 */
	public Books updateRatingsCount(Books book, int ratingsCount) throws SQLException {
		String updateBook = "UPDATE BOOKS SET ratingsCount=? WHERE title=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBook);
			updateStmt.setInt(1, ratingsCount);
			updateStmt.setString(2, book.getTitle());
			updateStmt.executeUpdate();

			// Update the book params before returning to the caller.
			book.setRatingsCount(ratingsCount);
			return book;
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
	
	public List<Books> getBooksFromTitle(String title)
			throws SQLException {
		List<Books> bookslist = new ArrayList<Books>();
//		String selectBooks ="SELECT title,description"
//				+ "FROM BOOKS WHERE title=?;";
		
		String selectBooks = "SELECT title,description,authors, publisher,categories,publishedDate,ratingsCount FROM BookReview.BOOKS WHERE BookReview.BOOKS.title=?";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		//PublishersDao publishersDao =  PublishersDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBooks);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String title1 = results.getString("title");
				String description = results.getString("description");
				String resultAuthor = results.getString("authors");
				//Publishers publisher = publishersDao.getPublisherByUserName("publisher");
				//String publisherName = publisher.getUserName();
				String categories = results.getString("categories");
				Date publishedDate = results.getDate("publishedDate");
				int ratingCount = results.getInt("ratingsCount");
			
				Books book =
						new Books(title1, description, resultAuthor,categories,publishedDate,ratingCount);
				bookslist.add(book);
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

	
}