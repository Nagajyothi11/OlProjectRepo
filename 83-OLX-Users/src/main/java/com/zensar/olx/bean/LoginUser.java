package com.zensar.olx.bean;

public class LoginUser {
	private String userName;
	private String password;
	/**
	 * @param userName
	 * @param password
	 */
	public LoginUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	/**
	 * @param userName
	 */
	public LoginUser(String userName) {
		super();
		this.userName = userName;
	}
	/**
	 * 
	 */
	public LoginUser() {
		super();
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginUser [userName=" + userName + ", password=" + password + "]";
	}

}
