package reader.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reader.model.*;

public class ReviewsDao {
	protected ConnectionManager connectionManager;
	
	
	private static ReviewsDao  instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	
	
//	CREATE TABLE REVIEWS(
//			reviewId INT NOT NULL AUTO_INCREMENT,
//			title VARCHAR(255), /** From BOOK*/
//			userName VARCHAR(255),
//			score INT,
//			shortSummary TEXT,
//			summary TEXT,
//			helpfulness INT,
//			CONSTRAINT pk_REVIEWS_reviewId PRIMARY KEY(reviewId),
//			CONSTRAINT fk_REVIEWS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
//			ON UPDATE CASCADE ON DELETE CASCADE
//			);

	
	/** CREATE */
	public Reviews create(Reviews review) throws SQLException {
		String insertReview = 
				"INSERT INTO REVIEWS(title,userName,score,shortSummary,summary,helpfulness)"
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			
			// Create auto-generated reviewId
			insertStmt = connection.prepareStatement(insertReview,
					Statement.RETURN_GENERATED_KEYS);	
			insertStmt.setString(1, review.getBook().getTitle());
			insertStmt.setString(2, review.getUser().getUserName());
			insertStmt.setInt(3, review.getScore());
			insertStmt.setString(4, review.getShortSummary());
			insertStmt.setString(5, review.getSummary());
			insertStmt.setInt(6, review.getHelpfulness());
			insertStmt.executeUpdate();
				
			return review;
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
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM REVIEWS WHERE reviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
	/** <List>get by books */
	public List<Reviews> getReviewsByBooks(Books book) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT reviewId, title, userName, score, shortSummary, summary, helpfulness" +
			" FROM REVIEWS WHERE title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, book.getTitle());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			BooksDao booksDao = BooksDao.getInstance();
			
			while(results.next()) {
				int reviewId = results.getInt("reviewId");
				
				//Get book title
				String title = results.getString("title");
				Books resultBook = booksDao.getBookByTitle(title);
				
				//Get username
				String username_get_from_query = results.getString("username");
				Users username = usersDao.getUserByUserName(username_get_from_query);
				
				int score = results.getInt("score");
				String shortSummary = results.getString("shortSummary");
				String summary = results.getString("summary");
				int helpfulness = results.getInt("helpfulness");
				
				Reviews review = new Reviews(reviewId, resultBook, username, score, shortSummary, summary, helpfulness);
				
				reviews.add(review);
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
		return reviews;
	}
	
	
	/** <List>get by users */
	public List<Reviews> getReviewsByUser(Users user) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT reviewId, title, userName, score, shortSummary, summary, helpfulness" +
			"FROM REVIEWS WHERE userName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			
			BooksDao booksDao = BooksDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				int reviewId = results.getInt("reviewId");

				//Get book title
				String title = results.getString("title");
				Books book = booksDao.getBookByTitle(title);
				
				//Get username
				String username = results.getString("username");
				Users resultUsername = usersDao.getUserByUserName(username);
				
				
				int score = results.getInt("score");
				String shortSummary = results.getString("shortSummary");
				String summary = results.getString("suammry");
				int helpfulness = results.getInt("helpfulness");
				
				Reviews review = new Reviews(reviewId, book, resultUsername, score, shortSummary, summary, helpfulness);
				
				reviews.add(review);
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
		return reviews;
	}
	
	
	/** get Review by id */
	
	public Reviews getReviewById(int id) throws SQLException {
		String selectReview = 
				" SELECT reviewId, title, userName, score, shortSummary, summary, helpfulness "
				+ "FROM REVIEWS WHERE reviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			BooksDao booksDao = BooksDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				int resultId = results.getInt("reviewId");
				
				//Get book title
				String title = results.getString("title");
				Books book = booksDao.getBookByTitle(title);
				
				//Get username
				String username = results.getString("username");
				Users user = usersDao.getUserByUserName(username);
				
				int score = results.getInt("score");
				String shortSummary = results.getString("shortSummary");
				String summary = results.getString("summary");
				int helpfulness = results.getInt("helpfulness");
				
				Reviews review =
						new Reviews(resultId, book, user, score, shortSummary, summary, helpfulness);
				return review;
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
	
	
	

	
	
	/** UPDATE helpfulness
	 */
	public Reviews updateHelpfulness(Reviews review, int helpfulness) throws SQLException {
		//String updateReview = "UPDATE REVIEWS SET helpfulness=? WHERE reviewId=?;";
		String updateReview = "UPDATE REVIEWS SET helpfulness=? WHERE title=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setInt(1, helpfulness);
			//updateStmt.setInt(2, review.getReviewId());
			updateStmt.setString(2, review.getBook().getTitle());
			updateStmt.executeUpdate();

			// Update the review params before returning to the caller.
			review.setHelpfulness(helpfulness);;
			return review;
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
