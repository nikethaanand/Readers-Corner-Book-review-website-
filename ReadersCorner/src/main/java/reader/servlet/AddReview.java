package reader.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reader.dal.BooksDao;
import reader.dal.ReviewsDao;
import reader.dal.UsersDao;
import reader.model.Books;
import reader.model.Reviews;
import reader.model.Users;

//CREATE TABLE REVIEWS(
//reviewId INT NOT NULL AUTO_INCREMENT,
//title varchar(255),
//userName VARCHAR(255),
//score INT,
//shortSummary TEXT,
//summary TEXT,
//helpfulness INT,
//CONSTRAINT pk_REVIEWS_reviewId PRIMARY KEY(reviewId),
//CONSTRAINT fk_REVIEWS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
//ON UPDATE CASCADE ON DELETE CASCADE
//);

@WebServlet("/addreview")
public class AddReview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected ReviewsDao reviewsDao;
	protected UsersDao usersDao;
	protected BooksDao booksDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
		usersDao = UsersDao.getInstance();
		booksDao = BooksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddReview.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        	String title=req.getParameter("title");
        	Books book = new Books(title);
        	String userName=req.getParameter("userName");
        	Users user = new Users(userName);
        	int score = Integer.parseInt(req.getParameter("score"));
        	String shortSummary=req.getParameter("shortSummary");
        	String summary=req.getParameter("summary");
        	int helpfulness = Integer.parseInt(req.getParameter("helpfulness"));
        	
        	System.out.print("title: " + title);
        	System.out.print(", username: " + userName);// Fix this ..this is null 
        	System.out.print(", user: " + user); // results in giving user address instead of user
           	System.out.print(", score: " + score);
        	System.out.print(", short summary: " + shortSummary);
        	System.out.print(" summary: " + summary);
           	System.out.print(", helpfulness: " + helpfulness);

  
	        try {
	        	Reviews review = 
	        			new Reviews(book, user, score, shortSummary, summary, helpfulness);
	        	review = reviewsDao.create(review);
	        	messages.put("success", "Successfully created a new review for book with title.. " + title);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
        
        req.getRequestDispatcher("/AddReview.jsp").forward(req, resp);
    }
}