package edu.handong.csee.java.chatcounter;

public class Format {
	String date;
	String user;
	String message;
	public Format() {}
	public Format(String date, String user, String message) {
		this.date=date;
		this.user=user;
		this.message=message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
