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

@WebServlet("/deletebookcollection")
public class DeleteBookCollection extends HttpServlet {
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
       
    public DeleteBookCollection() {
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
        req.getRequestDispatcher("/DeleteBookCollection.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
    	String title = req.getParameter("title");
		int collectionId = Integer.parseInt(req.getParameter("bookcollectionId"));

        if (userName == null ) {
            messages.put("title", "Invalid Input");
            messages.put("disableSubmit", "true");
        } else {
        	Collections newCollection = null;
			try {
				newCollection = collectionsDao.getCollectionById(collectionId);
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException(e1);
			}
        	Books newBook = null;
			try {
				newBook = booksDao.getBookByTitle(title);
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException(e1);
			}
        	BookCollections newBookCollection = null;
			try {
				newBookCollection = bookCollectionDao.getBookCollectionById(collectionId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
	        	newBookCollection = bookCollectionDao.delete(newBookCollection);
	        	// Update the message.
		        if (newBookCollection == null) {
		            messages.put("title", "Successfully deleted " + title + " from collection");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete" + title + " from collection");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteBookCollection.jsp").forward(req, resp);
    }

}
