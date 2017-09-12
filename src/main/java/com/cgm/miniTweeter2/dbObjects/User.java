package com.cgm.miniTweeter2.dbObjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cgm.miniTweeter2.DTO.MessageDTO;
import com.cgm.miniTweeter2.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }, name = "uk_username") })
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "about")
	private String about;

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
	private List<User> friends;

	// @JsonIgnore
	// @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	// @JoinColumn()
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = new ArrayList<User>(friends);
	}

	public void addFriend(User user) {
		this.friends.add(user);
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public void removeFriend(User user) {
		friends.remove(user);
	}

	public UserDTO asDTO() {
		UserDTO output = new UserDTO();

		output.setName(this.name);
		output.setUsername(this.username);
		output.setAbout(this.about);

		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		List<UserDTO> friendsDTO = new ArrayList<UserDTO>();
		for (Message message : messages) {
			messagesDTO.add(message.asDTOnoReference());
		}
		for (User friend : friends) {
			friendsDTO.add(friend.asDTOnoReference());
		}
		return output;
	}

	public UserDTO asDTOnoReference() {
		UserDTO output = new UserDTO();
		output.setName(this.name);
		output.setUsername(this.username);
		output.setAbout(this.about);
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		User other = (User) obj;
		if (id != other.getId())
			return false;
		return true;
	}

}
