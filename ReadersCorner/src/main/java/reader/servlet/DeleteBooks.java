package reader.servlet;
import reader.dal.*;
import reader.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bookdelete")
public class DeleteBooks extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Book");        
        req.getRequestDispatcher("/DeleteBooks.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("title", "Invalid title");
            messages.put("disableSubmit", "true");
        } else {
	        Books book = new Books(title);
	        try {
	        	book = booksDao.delete(book);
	        	// Update the message.
		        if (book == null) {
		            messages.put("title", "Successfully deleted " + title);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + title);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteBooks.jsp").forward(req, resp);
    }
}
//
//package reader.servlet;
//
//
//
//import reader.dal.*;
//import reader.model.*;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.annotation.*;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/bookdelete")
//public class DeleteBooks extends HttpServlet {
//	
//	private static final long serialVersionUID = 1L;
//	protected BooksDao booksDao;
//	
//	@Override
//	public void init() throws ServletException {
//		booksDao = BooksDao.getInstance();
//	}
//	
//	@Override
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//        // Provide a title and render the JSP.
//        messages.put("title", "Delete User");        
//        req.getRequestDispatcher("/DeleteBooks.jsp").forward(req, resp);
//	}
//	
//	@Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp)
//    		throws ServletException, IOException {
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//
//        // Retrieve and validate name.
//        String userName = req.getParameter("username");
//        if (userName == null || userName.trim().isEmpty()) {
//            messages.put("title", "Invalid UserName");
//            messages.put("disableSubmit", "true");
//        } else {
//        	// Delete the BlogUser.
//	        Users user = new Users(userName);
//	        try {
//	        	user = usersDao.delete(user);
//	        	// Update the message.
//		        if (user == null) {
//		            messages.put("title", "Successfully deleted " + userName);
//		            messages.put("disableSubmit", "true");
//		        } else {
//		        	messages.put("title", "Failed to delete " + userName);
//		        	messages.put("disableSubmit", "false");
//		        }
//	        } catch (SQLException e) {
//				e.printStackTrace();
//				throw new IOException(e);
//	        }
//        }
//        
//        req.getRequestDispatcher("/DeleteBooks.jsp").forward(req, resp);
//    }
//}