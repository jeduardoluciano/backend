package br.com.appdosamba.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.appdosamba.backend.model.common.AbstractEntity;
import br.com.appdosamba.backend.model.common.Address;
@Entity
@JsonTypeName("places")
public class CommercialPlace extends  AbstractEntity{

	private static final long serialVersionUID = 1L;
	private String name;
	private String website;
	private String fanpage;
	private Boolean approved;
	private String description;
	private String logo;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy="place", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Event> event = new ArrayList<>();
	

	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Event> getEvent() {
		return event;
	}
	public void setEvent(List<Event> event) {
		this.event = event;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getFanpage() {
		return fanpage;
	}
	public void setFanpage(String fanpage) {
		this.fanpage = fanpage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "CommercialPlace [name=" + name + ", website=" + website + ", fanpage=" + fanpage + ", approved="
				+ approved + ", description=" + description + ", logo=" + logo + ", address=" + address + ", event="
				+ event + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((approved == null) ? 0 : approved.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((fanpage == null) ? 0 : fanpage.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommercialPlace other = (CommercialPlace) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (approved == null) {
			if (other.approved != null)
				return false;
		} else if (!approved.equals(other.approved))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (fanpage == null) {
			if (other.fanpage != null)
				return false;
		} else if (!fanpage.equals(other.fanpage))
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}	
	
	
}
