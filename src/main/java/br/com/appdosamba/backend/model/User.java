package br.com.appdosamba.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.com.appdosamba.backend.infra.MD5;
import br.com.appdosamba.backend.model.common.AbstractEntity;
import br.com.appdosamba.backend.model.common.Gender;
import br.com.appdosamba.backend.model.common.Profile;

@Entity
public class User extends  AbstractEntity{

	private static final long serialVersionUID = -1019636109156346145L;
	
	private String firstName;
	private String lastName;
	private Boolean approved;
	private Gender gender;
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	@Email
	private String login;

	@Column(length = 150, updatable=false )
	private String password;
	
	@NotNull
	@Column(length = 18)
	@Enumerated(EnumType.STRING)
	private Profile profile;
	
	@Column(length = 255)
	@NotNull
	private String photo = "https://s3.amazonaws.com/app-samba/img/users/defaut.png";
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastLogin;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public DateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = MD5.crypt(password);
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
