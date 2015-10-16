package br.com.appdosamba.backend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import br.com.appdosamba.backend.model.common.AbstractEntity;

@Entity
public class Event extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String image;
	private String entrance;
	private String beer;
	private String phone;
	private String website;
	private Boolean approved;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	@ManyToOne(fetch = FetchType.EAGER)
	private CommercialPlace place;
	@Transient
	private String hour;

	public String getHour() {
		String iHour = null;

		if (this.date != null)
			iHour = this.date.getHourOfDay() + ":" + this.date.getMinuteOfHour();

		return iHour;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getBeer() {
		return beer;
	}

	public void setBeer(String beer) {
		this.beer = beer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public CommercialPlace getPlace() {
		return place;
	}

	public void setPlace(CommercialPlace place) {
		this.place = place;
	}

	public void setHour() {
	
		String iHour = null;

		if (this.date != null)
			iHour = this.date.getHourOfDay() + ":" + this.date.getMinuteOfHour();
		
		this.hour = iHour;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	
	
	

}
