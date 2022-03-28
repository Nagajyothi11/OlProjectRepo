package com.zensar.olx.bean;

public class NewAdvertisementPostRequest {
	int id;
	String title;
	int categoryId;
	String description;
	double price;
	int statusId;
	/**
	 * @param id
	 * @param title
	 * @param categoryid
	 * @param description
	 * @param price
	 */
	public NewAdvertisementPostRequest(int id, String title, int categoryId, String description, double price) {
		super();
		this.id = id;
		this.title = title;
		categoryId = categoryId;
		this.description = description;
		this.price = price;
	}
	/**
	 * @param id
	 */
	public NewAdvertisementPostRequest(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * 
	 */
	public NewAdvertisementPostRequest() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the Categoryid
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryid the categoryid to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewAdvertisementPostRequest [id=" + id + ", title=" + title + ", CategoryId=" + categoryId
				+ ", description=" + description + ", price=" + price + "]";
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
		NewAdvertisementPostRequest other = (NewAdvertisementPostRequest) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}