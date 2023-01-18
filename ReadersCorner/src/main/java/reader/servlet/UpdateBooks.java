package reader.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reader.dal.*;
import reader.model.*;


@WebServlet("/bookupdate")
public class UpdateBooks extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected BooksDao booksDao;
	
	@Override
	public void init() throws ServletException {
		booksDao = BooksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        System.out.print("Book Update");
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String title = req.getParameter("title");
        
//        if (userName == null || userName.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid UserName.");
//        }
//        else {
        	try {
        		Books book = booksDao.getBookByTitle(title);
        		if(book == null) {
        			messages.put("success", "Enter a book title.");
        		}
        		req.setAttribute("title",book);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
        
        req.getRequestDispatcher("/UpdateBooks.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String title = req.getParameter("title");
        

        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	try {
        		Books book = booksDao.getBookByTitle(title);
        		if(book == null) {
        			messages.put("success", "Title does not exist update cannot be performed");
        		} else {
        			int newRating = Integer.parseInt(req.getParameter("ratingsCount"));

        			
        				book = booksDao.updateRatingsCount(book,newRating);
        	        	messages.put("success", "Successfully updated " + title);
        			
        		}
        		req.setAttribute("Book", book);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
     
        req.getRequestDispatcher("/UpdateBooks.jsp").forward(req, resp);
    }
}


