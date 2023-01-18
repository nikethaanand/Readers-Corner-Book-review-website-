package reader.model;

public class Reviews{
	protected int reviewId;
	protected Books book;
	protected Users user;
	protected int score;
	protected String shortSummary;
	protected String summary;
	protected int helpfulness;
	

	public Reviews(int reviewId, Books book, Users user, int score, String shortSummary, String summary,
			int helpfulness) {
		super();
		this.reviewId = reviewId;
		this.book = book;
		this.user = user;
		this.score = score;
		this.shortSummary = shortSummary;
		this.summary = summary;
		this.helpfulness = helpfulness;
	}

	public Reviews(Books book, Users user, int score, String shortSummary, String summary, int helpfulness) {
		super();
		this.book = book;
		this.user = user;
		this.score = score;
		this.shortSummary = shortSummary;
		this.summary = summary;
		this.helpfulness = helpfulness;
	}

	public Reviews(Books book, Users user, int score, String shortSummary, String summary) {
		super();
		this.book = book;
		this.user = user;
		this.score = score;
		this.shortSummary = shortSummary;
		this.summary = summary;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getShortSummary() {
		return shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getHelpfulness() {
		return helpfulness;
	}

	public void setHelpfulness(int helpfulness) {
		this.helpfulness = helpfulness;
	}
	
}