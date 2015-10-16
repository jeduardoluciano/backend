package br.com.appdosamba.backend.model.common;

import javax.persistence.Entity;

@Entity
public class Address extends  AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private long number;
	private String district;
	private String city;
	private String lat;
	private String lng;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Address [name=" + name + ", number=" + number + ", district=" + district + ", city=" + city + ", lat="
				+ lat + ", lng=" + lng + ", phone=" + phone + "]";
	}
	
	
	
	
}