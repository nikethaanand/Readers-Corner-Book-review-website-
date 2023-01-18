package reader.model;import java.sql.Date;

public class Books {
	protected String title;
	protected String description;
	protected String authors;
	protected Publishers publisher;
	protected String categories;
	protected Date publishedDate;
	protected int ratingsCount;
	
	public Books(String title, String description, String authors, Publishers publisher, String categories,
			Date publishedDate, int ratingsCount) {
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.publisher = publisher;
		this.categories = categories;
		this.publishedDate = publishedDate;
		this.ratingsCount = ratingsCount;
	}

	
	public Books(String title, String description, Publishers publisher, String categories, Date publishedDate,
			int ratingsCount) {
		super();
		this.title = title;
		this.description = description;
		this.publisher = publisher;
		this.categories = categories;
		this.publishedDate = publishedDate;
		this.ratingsCount = ratingsCount;
	}
	
	public Books(String title, String description, String authors, String categories, Date publishedDate,
			int ratingsCount) {
		super();
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.categories = categories;
		this.publishedDate = publishedDate;
		this.ratingsCount = ratingsCount;
	}


	public Books(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public Books(String title) {
		super();
		this.title = title;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Publishers getPublisher() {
		return publisher;
	}

	public void setPublisher(Publishers publisher) {
		this.publisher = publisher;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public int getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}
	
}