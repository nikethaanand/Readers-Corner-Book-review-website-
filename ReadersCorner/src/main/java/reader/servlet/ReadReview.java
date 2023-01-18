package reader.servlet;

import reader.dal.*;
import reader.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/readreview")
public class ReadReview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected ReviewsDao reviewsDao;
	protected BooksDao booksDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
		booksDao = BooksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();
        

       String book_title = req.getParameter("title");
       Books book = null;
		try {
			book = booksDao.getBookByTitle(book_title);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        if (book_title  == null || book_title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid book to search for review.");
        } else {

        	try {
        		reviews = reviewsDao.getReviewsByBooks(book);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + book_title);
        
        	
        	messages.put("previousTitle", book_title);
        }
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/ReadReviewsByBook.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
       
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();
        
        // Get book title from query
        String book_title = req.getParameter("title");
        System.out.println("book title: " + book_title);
        Books book = null;
		try {
			book = booksDao.getBookByTitle(book_title);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
        if (book == null || book_title.trim().isEmpty()) {
        	System.out.println("book:" + book);
            messages.put("failed", "Please enter a valid book title.");
           
        } else {

        	try {
            	reviews = reviewsDao.getReviewsByBooks(book);
            	System.out.println("review:" + reviews);
            
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying reviews for " + book_title);
        }
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/ReadReviewsByBook.jsp").forward(req, resp);
    }
}