package com.cgm.miniTweeter2.dbObjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cgm.miniTweeter2.DTO.MessageDTO;

//import org.hibernate.annotations.Entity;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

	private static final long serialVersionUID = -2941181636782789264L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "messages_id_seq", sequenceName = "messages_id_seq", allocationSize = 1)
	private long id;

	// @Column(name="user_id")
	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "userid")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "timestamp")
	private String time;

	@Column(name = "message")
	private String message;

	public Message() {
	}

	public Message(String message, User user) {
		// this.id = Long.MAX_VALUE;
		this.message = message;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public MessageDTO asDTO() {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setId(this.id);
		messageDTO.setMessage(this.message);
		messageDTO.setUser(this.user.asDTOnoReference());

		return messageDTO;
	}

	public MessageDTO asDTOnoReference() {
		MessageDTO messageDTO = new MessageDTO();

		messageDTO.setId(this.id);
		messageDTO.setMessage(this.message);
		// messageDTO.setUser(this.user.asDTOnoReference());

		return messageDTO;
	}
}
