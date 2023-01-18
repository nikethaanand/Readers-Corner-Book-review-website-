package reader.model;

public class Users {

	protected String userName;
	protected String password;
	
	public Users(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	
	public Users(String userName) {
		super();
		this.userName = userName;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}