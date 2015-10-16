package br.com.appdosamba.backend.model.common;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@MappedSuperclass
public class AbstractEntity implements java.io.Serializable{
	private static final long serialVersionUID = 956419232222987050L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private  DateTime createdDate = new DateTime();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}
	
	
	
	
}
