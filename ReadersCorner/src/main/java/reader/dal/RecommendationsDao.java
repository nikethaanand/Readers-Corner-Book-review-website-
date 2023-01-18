package reader.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import reader.model.*;


public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertComment =
			"INSERT INTO Recommendations(title,userName,content,created) VALUES(?,?,?,?);";
		
		 
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertComment,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendation.getBook().getTitle());
			insertStmt.setString(2, recommendation.getUser().getUserName());
			insertStmt.setString(3, recommendation.getContent());
			insertStmt.setTimestamp(4, new Timestamp(recommendation.getCreated().getTime()));
			
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if(resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationsId(recommendationId);
			return recommendation;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	
	public Recommendations updateContent(Recommendations recommendationContent, String content) throws SQLException {
		String updateRecommendation = "UPDATE Recommendations SET content=?,created=? WHERE recomendationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecommendation);
			updateStmt.setString(1, content);
			Timestamp newCreatedTime = new Timestamp(0);
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTime.getTime()));
			updateStmt.setInt(3, recommendationContent.getRecommendationsId());
			updateStmt.executeUpdate();

			recommendationContent.setContent(content);
			recommendationContent.setCreated(newCreatedTime);
			return recommendationContent;
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


	public Recommendations delete(Recommendations recommendation) throws SQLException
	{
		String deleterecommendation = "DELETE FROM Recommendations WHERE recommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleterecommendation);
			deleteStmt.setInt(1,recommendation.getRecommendationsId());
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


	
	public Recommendations getRecommendationsById(int recommendationId) throws SQLException

    {
        String selectRecommendation =
            "SELECT recommendationId,title,userName,content,created FROM Recommendations " +
            "WHERE recommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, recommendationId);
            results = selectStmt.executeQuery();
            UsersDao usersDao=UsersDao.getInstance();
            BooksDao booksDao=BooksDao.getInstance();
            if(results.next()) {
                String title = results.getString("title");
                String content = results.getString("content");
                Timestamp created =  new Timestamp(results.getTimestamp("created").getTime());
                String userName = results.getString("UserName");
                Users users=usersDao.getUserByUserName(userName);
                Books books=booksDao.getBookByTitle(title);
                
            Recommendations recommendations1 =  new Recommendations(recommendationId,books, users, content,created);
            return recommendations1;

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
	
	public List<Recommendations> getRecommendationsByTitle(Books book) throws SQLException {
		List<Recommendations> title = new ArrayList<Recommendations>();
		String selectTitle =
		"SELECT recommendationId,title,userName,content,created FROM Recommendations " +
		"WHERE title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTitle);
			selectStmt.setString(1, book.getTitle());
			results = selectStmt.executeQuery();
			UsersDao usersDao=UsersDao.getInstance();
			
			while(results.next()) {
				int recommendationId = results.getInt("recommendationId");
				String content = results.getString("Content");
				String userName=results.getString("userName");
				Timestamp created =  new Timestamp(results.getTimestamp("created").getTime());
				Users users=usersDao.getUserByUserName(userName);
				
				Recommendations Recommendations1 =
						new Recommendations(recommendationId, book, users, content, created);
				title.add(Recommendations1);
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
		return title;
	}
	

	
	public List<Recommendations> getRecommendationsByUserId(Users user) throws SQLException {
		List<Recommendations> title = new ArrayList<Recommendations>();
		String selectTitle =
		"SELECT recommendationId,title,userName,content,created,FROM Recommendations " +
		"WHERE userName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTitle);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			BooksDao booksDao=BooksDao.getInstance();
			while(results.next()) {
				int recommendationId = results.getInt("recommendationId");
				String content = results.getString("Content");
				String title1=results.getString("title");
				Timestamp created =  new Timestamp(results.getTimestamp("created").getTime());
				Books books=booksDao.getBookByTitle(title1);
				
				Recommendations Recommendations1 = new Recommendations(recommendationId, books, user, content, created);
				title.add(Recommendations1);
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
		return title;
	}

}