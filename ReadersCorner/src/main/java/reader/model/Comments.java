package reader.model;




public class Comments{
	protected int commentId;
	protected Recommendations recommendation;
	protected Users user;
	protected String comment;
	
	public Comments(int commentId, Recommendations recommendation, Users user, String comment) {
		super();
		this.commentId = commentId;
		this.recommendation = recommendation;
		this.user = user;
		this.comment = comment;
	}

	public Comments(Recommendations recommendation, Users user, String comment) {
		super();
		this.recommendation = recommendation;
		this.user = user;
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Recommendations getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Recommendations recommendation) {
		this.recommendation = recommendation;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}