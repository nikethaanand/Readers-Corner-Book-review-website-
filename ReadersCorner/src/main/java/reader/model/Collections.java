package reader.model;

public class Collections{
	protected int collectionId;
	protected String collectionName;
	protected Users user;
	//protected String username;
	
	public Collections(int collectionId, String collectionName, Users user) {
		super();
		this.collectionId = collectionId;
		this.collectionName = collectionName;
		this.user = user;
	}


	public Collections(String collectionName, Users user) {
		super();
		this.collectionName = collectionName;
		this.user = user;
	}
	
//	public Collections(String collectionName, String username) {
//		super();
//		this.collectionName = collectionName;
//		this.username = username;
//	}
	
	public int getCollectionId() {
		return collectionId;
	}


	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}


	public String getCollectionName() {
		return collectionName;
	}


	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	
}