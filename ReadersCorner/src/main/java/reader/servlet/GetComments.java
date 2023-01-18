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

import reader.dal.CommentsDao;
import reader.dal.RecommendationsDao;
import reader.dal.UsersDao;
import reader.model.Comments;
import reader.model.Readlist;



@WebServlet("/getcomments")
public class GetComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected UsersDao usersDao;
	protected RecommendationsDao recommendationDao;
	protected CommentsDao commentsDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		recommendationDao = RecommendationsDao.getInstance();
		commentsDao = CommentsDao.getInstance();
	}
       
    public GetComments() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Comments> comments = new ArrayList<Comments>();
        

        String recIdStr = req.getParameter("userName");
        if (recIdStr == null || recIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
        		int recId = Integer.valueOf(recIdStr);
            	comments = commentsDao.getCommentsByRecommendationId(recId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + recIdStr);
        
        	
        	messages.put("previousTitle", recIdStr);
        }
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/GetComments.jsp").forward(req, resp);
	}
       

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Comments> comments = new ArrayList<Comments>();
        

        String recIdStr = req.getParameter("recommendationId");
        if (recIdStr == null || recIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
        		int recId = Integer.valueOf(recIdStr);
            	comments = commentsDao.getCommentsByRecommendationId(recId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + recIdStr);
        
        	
        	messages.put("previousTitle", recIdStr);
        }
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/GetComments.jsp").forward(req, resp);
    }

}
