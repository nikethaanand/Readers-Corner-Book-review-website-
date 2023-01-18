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


@WebServlet("/findcollections")
public class FindCollections extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected CollectionsDao collectionsDao;
	
	@Override
	public void init() throws ServletException {
		collectionsDao = CollectionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Collections> collections = new ArrayList<Collections>();
        

        String username = req.getParameter("collectionName");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid User Name.");
        } else {

        	try {
        		collections = collectionsDao.getCollectionsByUserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + username);
        
        	
        	messages.put("previousTitle", username);
        }
        req.setAttribute("collections", collections);
        
        req.getRequestDispatcher("/FindCollections.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
       
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Collections> collections = new ArrayList<Collections>();
        
        
        String username = req.getParameter("userName");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid User Name.");
        } else {

        	try {
            	collections = collectionsDao.getCollectionsByUserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + username);
        }
        req.setAttribute("collections", collections);
        
        req.getRequestDispatcher("/FindCollections.jsp").forward(req, resp);
    }
}
