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


@WebServlet("/findbooks")
public class FindBooks extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected BooksDao booksDao;
	
	@Override
	public void init() throws ServletException {
		booksDao = BooksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Books> books = new ArrayList<Books>();
        

        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
            	books = booksDao.getBooksFromTitle(title);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        
        	
        	messages.put("previousTitle", title);
        }
        req.setAttribute("books", books);
        
        req.getRequestDispatcher("/FindBooks.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
       
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Books> books = new ArrayList<Books>();
        
        
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
            	books = booksDao.getBooksFromTitle(title);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        }
        req.setAttribute("books", books);
        
        req.getRequestDispatcher("/FindBooks.jsp").forward(req, resp);
    }
}
