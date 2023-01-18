package reader.model;

import java.sql.Timestamp;

public class Admins extends Users{
	protected Timestamp createdDate;

	public Admins(String userName, String password, Timestamp createdDate) {
		super(userName, password);
		this.createdDate = createdDate;
	}


	public Timestamp getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
}