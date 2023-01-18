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

import reader.dal.*;
import reader.model.*;

@WebServlet("/addreadlist")
public class AddBookToReadList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected ReadlistDao readlistDao;
	protected BooksDao booksDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		readlistDao = ReadlistDao.getInstance();
		booksDao = BooksDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
       
    public AddBookToReadList() {
        super();
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddBookToReadList.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String username = req.getParameter("username");
    	String title = req.getParameter("title");
        if (username == null || username.trim().isEmpty()
        		||title == null || title.trim().isEmpty()) {
            messages.put("success", "Invalid input.");
        } else {
	        try {
	        	Books book = booksDao.getBookByTitle(title);
	            if (book == null) {
	                messages.put("success", "Book not found.");
	            }
	        	Users user = usersDao.getUserByUserName(username);
	            if (user == null) {
	                messages.put("success", "User not found.");
	            }
	        	Readlist newReadList = new Readlist(user, book);
	        	newReadList = readlistDao.create(newReadList);
	        	
	        	messages.put("success", "Successfully added " + title + " to " + username + " readlist.");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddBookToReadList.jsp").forward(req, resp);
    }


}
