package com.zensar.olx.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Primary;

import com.sun.istack.NotNull;
@Embeddable
public class AdvertisementStatus {
	
	@Column(name="adv_status_id")
	private int id;
	@Transient
	private String status;

	/**
	 * @param id
	 * @param status
	 */
	public AdvertisementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	/**
	 * @param id
	 */
	public AdvertisementStatus(int id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 */
	public AdvertisementStatus() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdvStatus [id=" + id + ", status=" + status + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		AdvertisementStatus other = (AdvertisementStatus) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
