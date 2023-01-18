package reader.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reader.dal.BooksDao;
import reader.dal.ReadlistDao;
import reader.dal.UsersDao;
import reader.model.Books;
import reader.model.Readlist;

/**
 * Servlet implementation class GetBooksInReadList
 */
@WebServlet("/getbooksinreadlist")
public class GetBooksInReadList extends HttpServlet {
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
       
    public GetBooksInReadList() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Readlist> books = new ArrayList<Readlist>();
        

        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
            	books = readlistDao.getReadlistByuserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + username);
        
        	
        	messages.put("previousTitle", username);
        }
        req.setAttribute("books", books);
        req.getRequestDispatcher("/GetBooksInReadList.jsp").forward(req, resp);
  	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
   

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Readlist> books = new ArrayList<Readlist>();
        

        String username = req.getParameter("userName");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
            	books = readlistDao.getReadlistByuserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + username);
        
        	
        	messages.put("previousTitle", username);
        }
        req.setAttribute("books", books);
        req.getRequestDispatcher("/GetBooksInReadList.jsp").forward(req, resp);
    }

}
