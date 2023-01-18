package reader.model;


import java.sql.Timestamp;


public class Recommendations{
	protected int recommendationsId;
	protected Books book;
	protected Users user;
	protected String content;
	protected Timestamp created;
	
	public Recommendations(Books book, Users user, String content, Timestamp created) {
		super();
		this.book = book;
		this.user = user;
		this.content = content;
		this.created = created;
	}

	public Recommendations(int recommendationsId, Books book, Users user, String content, Timestamp created) {
		super();
		this.recommendationsId = recommendationsId;
		this.book = book;
		this.user = user;
		this.content = content;
		this.created = created;
	}
	public Recommendations(int recommendationsId)
	{
		super();
		this.recommendationsId = recommendationsId;

	}

	public int getRecommendationsId() {
		return recommendationsId;
	}

	public void setRecommendationsId(int recommendationsId) {
		this.recommendationsId = recommendationsId;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp newCreatedTime) {
		this.created = newCreatedTime;
	}
	
	
}