package com.zensar.olx.bean;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "olx_users_table")
public class OlxUser {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int olxUserId;
	@NotNull

	private String userName;
	@NotNull
	private String password;
	@NotNull
	private String role;
	@Enumerated(EnumType.STRING)
	private Active active;
	// @DefaultNull
	private String firstName;
	// @DefaultNull
	private String lastName;
	@NotNull
	private String phoneNumber;
	@NotNull
	private String emailId;

	/**
	 * @param olxUserId
	 * @param userName
	 * @param password
	 * @param role
	 * @param active
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param emailId
	 */
	public OlxUser(int olxUserId, String userName, String password, String role, Active active, String firstName,
			String lastName, String phoneNumber, String emailId) {
		super();
		this.olxUserId = olxUserId;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.active = active;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	/**
	 * 
	 */
	public OlxUser() {
		super();
	}

	/**
	 * @return the olxUserId
	 */
	public int getOlxUserId() {
		return olxUserId;
	}

	/**
	 * @param olxUserId
	 *            the olxUserId to set
	 */
	public void setOlxUserId(int olxUserId) {
		this.olxUserId = olxUserId;
	}

	/**
	 * @param olxUserId
	 */
	public OlxUser(int olxUserId) {
		super();
		this.olxUserId = olxUserId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the active
	 */
	public Active getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Active active) {
		this.active = active;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	
	@Override
	public String toString() {
		return "OLXUser [olxUserId=" + olxUserId + ", userName=" + userName + ", password=" + password + ", role="
				+ role + ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", emailId=" + emailId + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + olxUserId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OlxUser other = (OlxUser) obj;
		if (olxUserId != other.olxUserId)
			return false;
		return true;
	}

}
