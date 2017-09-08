package com.cgm.miniTweeter2.dbObjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import org.hibernate.annotations.Entity;




@Entity
@Table(name="messages")
public class Message implements Serializable {
	
	private static final long serialVersionUID = -2941181636782789264L;
	
	@Id
	//@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name = "messages_id_seq", sequenceName = "sbs_albums_id_seq", allocationSize = 1)
	private long id;
	@Column(name="user_id")
	private long userId;
	
	@Column(name="timestamp")
	private String time;
	
	@Column(name="message")
	private String message;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
