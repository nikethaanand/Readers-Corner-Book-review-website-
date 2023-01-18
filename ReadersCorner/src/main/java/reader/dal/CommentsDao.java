package reader.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reader.model.*;


public class CommentsDao {
	protected ConnectionManager connectionManager;

	private static CommentsDao instance = null;
	protected CommentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CommentsDao getInstance() {
		if(instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}

	 public Comments create(Comments newComment) throws SQLException {

	        String insertComment = "INSERT INTO Comments(recommendationId, userName, comment) VALUES(?,?,?);";

	        Connection connection = null;

	        PreparedStatement insertStmt = null;

	        ResultSet resultKey = null;

	        try {

	            connection = connectionManager.getConnection();

	            insertStmt = connection.prepareStatement(insertComment,

	                    Statement.RETURN_GENERATED_KEYS);

	            // PreparedStatement allows us to substitute specific types into the query template.

	            // For an overview, see:

	            // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.

	            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html

	            // For nullable fields, you can check the property first and then call setNull()

	            // as applicable.

	            insertStmt.setInt(1, newComment.getRecommendation().getRecommendationsId());

	            insertStmt.setString(2, newComment.getUser().getUserName());

	            insertStmt.setString(3, newComment.getComment());



	            

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

	            int commentId = -1;

	            if(resultKey.next()) {

	                commentId = resultKey.getInt(1);

	            } else {

	                throw new SQLException("Unable to retrieve auto-generated key.");

	            }

	            newComment.setCommentId(commentId);

	            return newComment;

	            

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
	 
//	public Comments create(Comments comment) throws SQLException {
//		String insertComment =
//			"INSERT INTO Comments(recommendationId,userName,comment) " +
//			"VALUES(?,?,?);";
//		Connection connection = null;
//		PreparedStatement insertStmt = null;
//		ResultSet resultKey = null;
//	
//		
//		try {
//			connection = connectionManager.getConnection();
//			insertStmt = connection.prepareStatement(insertComment,
//				Statement.RETURN_GENERATED_KEYS);
//			insertStmt.setInt(1, comment.getRecommendation().getRecommendationsId());
//			insertStmt.setString(2, comment.getRecommendation().getUser().getUserName());
//			insertStmt.setString(3, comment.getComment());
//
//			insertStmt.executeUpdate();
//			
//			resultKey = insertStmt.getGeneratedKeys();
//			int commentId = -1;
//			if(resultKey.next()) {
//				commentId = resultKey.getInt(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
//			comment.setCommentId(commentId);
//			return comment;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(insertStmt != null) {
//				insertStmt.close();
//			}
//			if(resultKey != null) {
//				resultKey.close();
//			}
//		}
//	}

	public Comments updateComment(Comments comments, String comment) throws SQLException {
		String updateComment = "UPDATE Comments SET comment=? WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateComment);
			updateStmt.setString(1, comment);
			updateStmt.setInt(2, comments.getCommentId());
			updateStmt.executeUpdate();

			comments.setComment(comment);
			return comments;
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


	public Comments delete(Comments Comment) throws SQLException {
		String deleteComment = "DELETE FROM Comments WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteComment);
			deleteStmt.setInt(1, Comment.getCommentId());
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
	

	
	public Comments getCommentsById(int commentId) throws SQLException
	{
		String selectComment =
				"SELECT commentId, recommendationId, userName, comment FROM COMMENTS " +
						"WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			UsersDao usersDao=UsersDao.getInstance();
			RecommendationsDao recommendationDao= RecommendationsDao.getInstance();

			if(results.next()) {
				String comment = results.getString("comment");
				String userName = results.getString("UserName");
				Users users=usersDao.getUserByUserName(userName);
				int recommendationId=results.getInt("recommendationId");
				Recommendations recommendation=recommendationDao.getRecommendationsById(recommendationId);
			    Comments comments1 = new Comments(commentId, recommendation, users, comment);
				return comments1;
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
	
	
 public List<Comments> getCommentsByName(String username) throws SQLException {
	List<Comments> comments = new ArrayList<Comments>();
	String selectComments =
			"SELECT commentId, recommendationId, userName, comment FROM COMMENTS " +
					"WHERE userName=?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectComments);
		selectStmt.setString(1, username);
		results = selectStmt.executeQuery();
		RecommendationsDao recommendationDao= RecommendationsDao.getInstance();
		UsersDao usersDao=UsersDao.getInstance();
		while(results.next()) {
			int commentId = results.getInt("commentId");
			String comment = results.getString("comment");
			int recommendationId=results.getInt("recommendationId");
			Recommendations recommendation=recommendationDao.getRecommendationsById(recommendationId);
			Users users= usersDao.getUserByUserName(username);
			Comments comment1 = new Comments(commentId,recommendation,users,comment);
			comments.add(comment1);
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
	return comments;
}
 
 public List<Comments> getCommentsByRecommendationId(int recId) throws SQLException {
	List<Comments> comments = new ArrayList<Comments>();
	String selectTitle =
	"SELECT commentId, recommendationId, userName, comment FROM COMMENTS " +
	"WHERE recommendationId=?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectTitle);
		selectStmt.setInt(1, recId);
		results = selectStmt.executeQuery();
		UsersDao usersDao=UsersDao.getInstance();
		RecommendationsDao recDao = RecommendationsDao.getInstance();
		while(results.next()) {
			int commentId = results.getInt("commentId");
			String comment = results.getString("comment");
			String userName = results.getString("UserName");
			Users users=usersDao.getUserByUserName(userName);
			Recommendations rec = recDao.getRecommendationsById(recId);
			Comments comment1 = new Comments(commentId,rec,users,comment);
			comments.add(comment1);
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
	return comments;
}
	
	
}