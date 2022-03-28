package com.zensar.olx.bean;

public class LoginResponse {
	private String jwt;

	/**
	 * @param jwt
	 */
	public LoginResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @param jwt the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginResponse [jwt=" + jwt + "]";
	}
	
	

}
