package reader.servlet;

import reader.dal.*;
import reader.model.*;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@WebServlet("/addrecommendation")
public class AddRecommendation extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected RecommendationsDao recommendationsDao;
	protected UsersDao usersDao;
	protected BooksDao booksDao;
	
	@Override
	public void init() throws ServletException {
		recommendationsDao = RecommendationsDao.getInstance();
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
        req.getRequestDispatcher("/AddRecommendation.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
      	
        	Date date = new Date();
        	Timestamp created =  new Timestamp(date.getTime());
        	String bookTitle=req.getParameter("title");
        	String userName = req.getParameter("userName");
        	String content = req.getParameter("content");
        	Users user = new Users(userName);
        	Books book = new Books(bookTitle);
        	
        	
        	System.out.print("bookTitle: " + bookTitle);
        	System.out.print("userName: "+ userName);
        	
        	if (bookTitle == null || bookTitle.trim().isEmpty() || userName == null || userName.trim().isEmpty()) {
	            messages.put("success", "Please enter a valid input.");
			}
        	else {
        		try {
        			Users usern = usersDao.getUserByUserName(userName);
    	        	Books bookn = booksDao.getBookByTitle(bookTitle);
    	        	
    	        	if(usern == null || bookn == null) {
            			messages.put("success", "Username or Book does not exist. ");
            		}
    	        	else {
    	        		Recommendations recommendation = new Recommendations(bookn, usern,content,created);
    	        		recommendation = recommendationsDao.create(recommendation);
    	        		messages.put("success", "Successfully created Recommendation of book: " + bookTitle);
    	        	}
    	        	
    	        } catch (SQLException e) {
    				e.printStackTrace();
    				throw new IOException(e);
    	        }
        		
        	}
	        
        
        
        req.getRequestDispatcher("/AddRecommendation.jsp").forward(req, resp);
    }
}
