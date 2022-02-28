package com.zensar.olx.bean;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name="advertises")
public class AdvertisementPost {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    
	private int id;
	@Column
	 private String title;
	
	@Column
	private String description;
	
	@Column
	private double price;
	
	@Embedded
	private Category category;
	
	@Embedded
	@Column(name="user_id")
	private OlxUser olxUser;
	
	@Embedded
	
	private AdvertisementStatus advertisementStatus;
	@Column
	private LocalDate createdDate;
	@Column
	private LocalDate modifiedDate;
	@Lob
	private byte[] photo;

	
	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param price
	 * @param category
	 * @param olxUser
	 * @param advertisementStatus
	 * @param createdDate
	 * @param modifiedDate
	 * @param photo
	 */
	public AdvertisementPost(int id, String title, String description, double price, Category category, OlxUser olxUser,
			AdvertisementStatus advertisementStatus, LocalDate createdDate, LocalDate modifiedDate, byte[] photo) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.olxUser = olxUser;
		this.advertisementStatus = advertisementStatus;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.photo = photo;
	}


	/**
	 * @param title
	 * @param description
	 * @param price
	 * @param category
	 * @param olxUser
	 * @param advertisementStatus
	 * @param createdDate
	 * @param modifiedDate
	 * @param photo
	 */
	public AdvertisementPost(String title, String description, double price, Category category, OlxUser olxUser,
			AdvertisementStatus advertisementStatus, LocalDate createdDate, LocalDate modifiedDate, byte[] photo) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.olxUser = olxUser;
		this.advertisementStatus = advertisementStatus;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.photo = photo;
	}


	/**
	 * 
	 */
	public AdvertisementPost() {
		super();
		this.createdDate=LocalDate.now();
		this.modifiedDate=LocalDate.now();
		
	}
	

	/**
	 * @param id
	 */
	public AdvertisementPost(int id) {
		super();
		this.id = id;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the olxUser
	 */
	public OlxUser getOlxUser() {
		return olxUser;
	}

	/**
	 * @param olxUser the olxUser to set
	 */
	public void setOlxUser(OlxUser olxUser) {
		this.olxUser = olxUser;
	}

	/**
	 * @return the advertisementStatus
	 */
	public AdvertisementStatus getAdvertisementStatus() {
		return advertisementStatus;
	}
	

	/**
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}


	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * @return the modifiedDate
	 */
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}


	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}


	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	/**
	 * @param advertisementStatus the advertisementStatus to set
	 */
	public void setAdvertisementStatus(AdvertisementStatus advertisementStatus) {
		this.advertisementStatus = advertisementStatus;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdvertisementPost [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", category=" + category + ", olxUser=" + olxUser + ", advertisementStatus=" + advertisementStatus
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", photo="
				+ Arrays.toString(photo) + "]";
	}

	
	
	
	
}
