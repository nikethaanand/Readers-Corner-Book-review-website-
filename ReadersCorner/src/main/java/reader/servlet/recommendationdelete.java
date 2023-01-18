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


@WebServlet("/recommendationdelete")
public class recommendationdelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected RecommendationsDao recommendationsDao;
	
	@Override
	public void init() throws ServletException {
		recommendationsDao = RecommendationsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Recommendation");        
        req.getRequestDispatcher("/recommendationdelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int recommendationId = Integer.parseInt(req.getParameter("recommendationId"));
        if (recommendationId == 0) {
            messages.put("title", "Invalid RecommendationId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	Recommendations recommendation = new Recommendations(recommendationId);
	        try {
	        	recommendation = recommendationsDao.delete(recommendation);
	        	// Update the message.
		        if (recommendation == null) {
		            messages.put("title", "Successfully deleted " + recommendationId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + recommendationId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/recommendationdelete.jsp").forward(req, resp);
    }
}