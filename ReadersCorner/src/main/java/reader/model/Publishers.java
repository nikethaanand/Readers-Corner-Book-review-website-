package reader.model;


import java.sql.Timestamp;

public class Publishers extends Users{
	
	protected Timestamp lastLogin;

	public Publishers(String userName, String password, Timestamp lastLogin) {
		super(userName, password);
		this.lastLogin = lastLogin;
	}
	
	

	public Publishers(String userName, String password) {
		super(userName, password);
	}

	public Publishers(String userName) {
		super(userName);
	}


	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getUsername() {
		return userName;
	}
	
}
