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


@WebServlet("/findrecommendations")
public class FindRecommendations extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected RecommendationsDao recommendationsDao;
	protected BooksDao booksDao;
	@Override
	public void init() throws ServletException {
		recommendationsDao = RecommendationsDao.getInstance();
		booksDao = BooksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        

        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
        		Books book = booksDao.getBookByTitle(title);
        		recommendations = recommendationsDao.getRecommendationsByTitle(book);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        
        	
        	messages.put("previousTitle", title);
        }
        req.setAttribute("recommendations", recommendations);
        
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
       
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        
        
        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {

        	try {
        		Books book = new Books(title);
        		recommendations = recommendationsDao.getRecommendationsByTitle(book);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        }
        req.setAttribute("recommendations", recommendations);
        
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
    }
}
