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

@WebServlet("/addcomments")
public class AddCommentsOnRecommendation extends HttpServlet {

	protected CommentsDao commentsDao;
	protected RecommendationsDao recommendationsDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		commentsDao = CommentsDao.getInstance();
		recommendationsDao = RecommendationsDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	private static final long serialVersionUID = 1L;
       
    public AddCommentsOnRecommendation() {
        super();
    }

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        req.getRequestDispatcher("/AddCommentsOnRecommendation.jsp").forward(req, resp);
    }
 

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String username = req.getParameter("userName");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Invalid username.");
        } else {
        	// Create the BlogUser.
        	String recommendationIdStr = req.getParameter("recommendationId");
        	int recommendationId = Integer.valueOf(recommendationIdStr);
        	String content = req.getParameter("comment");
	        try {
	        	Users user = usersDao.getUserByUserName(username);
	        	Recommendations rec = recommendationsDao.getRecommendationsById(recommendationId);
	        	Comments newComment = new Comments(rec, user, content);
	        	newComment = commentsDao.create(newComment);

	        	messages.put("success", "Successfully added comment to recommendation.");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddCommentsOnRecommendation.jsp").forward(req, resp);
    }

}