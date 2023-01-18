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

@WebServlet("/addbooktocollection")
public class AddBookToCollection extends HttpServlet {
	
	protected BookCollectionDao bookCollectionDao;
	protected BooksDao booksDao;
	protected CollectionsDao collectionsDao;
	
	@Override
	public void init() throws ServletException {
		bookCollectionDao = BookCollectionDao.getInstance();
		collectionsDao = CollectionsDao.getInstance();
		booksDao = BooksDao.getInstance();
	}
	
	private static final long serialVersionUID = 1L;
       
    public AddBookToCollection() {
        super();
    }

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddBookToCollection.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Invalid username.");
        } else {
        	// Create the BlogUser.
        	String collectionIdStr = req.getParameter("collectionId");
        	int collectionId = Integer.valueOf(collectionIdStr);
        	String title = req.getParameter("title");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Books newBook = booksDao.getBookByTitle(title);
	        	Collections newCollection = collectionsDao.getCollectionById(collectionId);
	        	BookCollections newBookCollection = new BookCollections(newCollection, newBook);
	        	newBookCollection = bookCollectionDao.create(newBookCollection);
	        	
	        	messages.put("success", "Successfully added " + title + " to collection.");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddBookToCollection.jsp").forward(req, resp);
    }

}
