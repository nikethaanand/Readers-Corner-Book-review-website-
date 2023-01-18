package reader.servlet;

import reader.dal.*;
import reader.model.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addbook")
public class AddBooks extends HttpServlet {
	
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
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddBooks.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
      	
        	
        	String title=req.getParameter("title");
        	String description=req.getParameter("description");
        	String authors=req.getParameter("authors");
        	String categories=req.getParameter("categories");

        	java.sql.Date publishedDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());


        	int ratingsCount = Integer.parseInt(req.getParameter("ratingsCount"));
        	
        	System.out.print("title: " + title);
        	System.out.print("description: " + description);
           	System.out.print("authors: " + authors);
        	System.out.print("categories: " + categories);
        	System.out.print("ratingsCount: " + ratingsCount);

  
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Books book = new Books(title, description,authors,categories,publishedDate,ratingsCount);
	        	book = booksDao.create(book);
	        	messages.put("success", "Successfully created " + title);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
        
        req.getRequestDispatcher("/AddBooks.jsp").forward(req, resp);
    }
}
