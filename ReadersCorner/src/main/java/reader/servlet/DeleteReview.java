package reader.servlet;
import reader.dal.*;
import reader.model.*;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/deletereview")
public class DeleteReview extends HttpServlet {
	
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
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete ");        
        req.getRequestDispatcher("/DeleteReview.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String title = req.getParameter("title");
        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        
        if (reviewId  == 0) {
            messages.put("title", "Invalid input");
            messages.put("disableSubmit", "true");
        } else {
        	Reviews searched_review = null; 
			try {
				searched_review = reviewsDao.getReviewById(reviewId);
				System.out.print("..searched review : " + searched_review);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	// Delete the review for selected book.;
	        try {
	        	searched_review = reviewsDao.delete(searched_review);
	        	// Update the outcome
		        if (searched_review == null) {
		            messages.put("title", "Successfully deleted review for book with reviewId=" + reviewId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete review for book with title..." + title);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteReview.jsp").forward(req, resp);
    }
}