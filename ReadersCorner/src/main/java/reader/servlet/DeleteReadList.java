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

/**
 * Servlet implementation class DeleteReadList
 */
@WebServlet("/deletereadlist")
public class DeleteReadList extends HttpServlet {
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReadList() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/DeleteReadList.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
		int readListId = Integer.parseInt(req.getParameter("readListId"));

        if (userName == null || userName.trim().isEmpty() || userName.trim().isEmpty()) {
            messages.put("title", "Invalid Input");
            messages.put("disableSubmit", "true");
        } else {
        	Users user = null;
			try {
				user = usersDao.getUserByUserName(userName);
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new IOException(e2);
			}
        	}
        	Readlist newReadlist=null;
			try {
				newReadlist = readlistDao.getReadlistById(readListId);
				System.out.print(newReadlist.getReadListId());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
	        	newReadlist = readlistDao.delete(newReadlist);
	        	// Update the message.
		        if (newReadlist == null) {
		            messages.put("title", "Successfully deleted " + readListId + " from readlist");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete" + readListId + " from collection");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	   
	        }
        req.getRequestDispatcher("/DeleteReadList.jsp").forward(req, resp);
    }

}
