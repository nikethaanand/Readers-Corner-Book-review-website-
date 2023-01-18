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

import reader.dal.BookCollectionDao;
import reader.dal.BooksDao;
import reader.dal.CollectionsDao;
import reader.model.Books;


@WebServlet("/getbooksincollection")
public class GetBooksInCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected BookCollectionDao bookCollectionDao;
	protected BooksDao booksDao;
	protected CollectionsDao collectionsDao;
	
	@Override
	public void init() throws ServletException {
		bookCollectionDao = BookCollectionDao.getInstance();
		collectionsDao = CollectionsDao.getInstance();
		booksDao = BooksDao.getInstance();
	}
       
    public GetBooksInCollection() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Books> books = new ArrayList<Books>();
        
        
        String collectionIdStr = req.getParameter("collectionId");
        int collectionId = Integer.valueOf(collectionIdStr);
        if (collectionIdStr == null || collectionIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a collectionId.");
        } else {
        	try {
            	books = bookCollectionDao.getBooksFromCollectionId(collectionId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for collectionId" + collectionIdStr);
        }
        req.setAttribute("books", books);
        
        req.getRequestDispatcher("/GetBooksInCollection.jsp").forward(req, resp);
    }
 

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
       
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Books> books = new ArrayList<Books>();
        
        
        String collectionIdStr = req.getParameter("collectionId");
        int collectionId = Integer.valueOf(collectionIdStr);
        if (collectionIdStr == null || collectionIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a collectionId.");
        } else {
        	try {
            	books = bookCollectionDao.getBooksFromCollectionId(collectionId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for collectionId" + collectionIdStr);
        }
        req.setAttribute("books", books);
        
        req.getRequestDispatcher("/GetBooksInCollection.jsp").forward(req, resp);
    }

}
