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


@WebServlet("/addcollection")
public class AddCollections extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected CollectionsDao collectionsDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		collectionsDao = CollectionsDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddCollections.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
      	
        	
        	String collectionName=req.getParameter("collectionName");
        	String userName = req.getParameter("userName");
        	Users user = new Users(userName);
        	
        	System.out.print("collectionName: " + collectionName);
        	System.out.print("userName: "+ userName);
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Collections collection = new Collections(collectionName,user);
	        	collection = collectionsDao.create(collection);
	        	messages.put("success", "Successfully created " + collectionName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        
        
        req.getRequestDispatcher("/AddCollections.jsp").forward(req, resp);
    }
}
