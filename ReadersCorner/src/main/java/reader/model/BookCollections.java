package reader.model;

public class BookCollections{
	protected int bookCollectionId;
	protected Collections collection;
	protected Books book;
	
	public BookCollections(int bookCollectionId, Collections collection, Books book) {
		super();
		this.bookCollectionId = bookCollectionId;
		this.collection = collection;
		this.book = book;
	}

	public BookCollections(Collections collection, Books book) {
		super();
		this.collection = collection;
		this.book = book;
	}

	public BookCollections(int bookCollectionId, Books book) {
		super();
		this.bookCollectionId = bookCollectionId;
		this.book = book;
	}

	public int getBookCollectionId() {
		return bookCollectionId;
	}

	public void setBookCollectionId(int bookCollectionId) {
		this.bookCollectionId = bookCollectionId;
	}

	public Collections getCollection() {
		return collection;
	}

	public void setCollection(Collections collection) {
		this.collection = collection;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}
	
}