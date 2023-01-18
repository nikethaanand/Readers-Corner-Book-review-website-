package reader.model;


public class Readlist{
	protected int readListId;
	protected Users user;
	protected Books book;
	
	public Readlist(int readListId, Users user, Books book) {
		super();
		this.readListId = readListId;
		this.user = user;
		this.book = book;
	}

	public Readlist(Users user, Books book) {
		super();
		this.user = user;
		this.book = book;
	}

	public int getReadListId() {
		return readListId;
	}

	public void setReadListId(int readListId) {
		this.readListId = readListId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}
	
	
}