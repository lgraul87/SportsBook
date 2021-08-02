package com.sportBook.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SportEvent")
public class SportEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, insertable = false, name = "id", nullable = false, length = 2)
	private Integer id;

	@Column(name = "name", nullable = false, length = 90)
	private String name;

	@Column(name = "score", nullable = false)
	private String score;

	@Column(name = "collaborators", nullable = false, length = 90)
	private String collaborators;

	@Column(name = "participants", nullable = false, length = 90)
	private String participants;

	@Column(name = "location", nullable = false, length = 90)
	private String location;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "time", nullable = false, length = 90)
	private String time;

	public SportEvent(Integer id, String name, String score, String collaborators, String participants, String location,
			Date date, String time) {

		setId(id);
		setName(name);
		setScore(score);
		setCollaborators(collaborators);
		setParticipants(participants);
		setLocation(location);
		setDate(date);
		setTime(time);

	}

	public SportEvent(Integer id, String score) {

		setId(id);
		setScore(score);
	}

	public SportEvent() {
	}

	public Integer getId() {
		return this.id;
	}

	private boolean setId(Integer id) {
		boolean valid = false;
		if (id != null && id > 0) {
			valid = true;
			this.id = id;
		}
		return valid;
	}

	public String getName() {
		return this.name;
	}

	private boolean setName(String name) {
		boolean valid = false;
		if (name != null && name.length() > 0) {
			valid = true;
			this.name = name;
		}
		return valid;
	}

	public String getScore() {
		return this.score;
	}

	private boolean setScore(String score) {
		boolean valid = false;
		if (score != null && score.length() > 0) {
			valid = true;
			this.score = score;
		}
		return valid;
	}

	public String getCollaborators() {
		return this.collaborators;
	}

	private boolean setCollaborators(String collaborators) {
		boolean valid = false;
		if (collaborators != null && collaborators.length() > 0) {
			valid = true;
			this.collaborators = collaborators;
		}
		return valid;
	}

	public String getParticipants() {
		return this.participants;
	}

	private boolean setParticipants(String participants) {
		boolean valid = false;
		if (participants != null && participants.length() > 0) {
			valid = true;
			this.participants = participants;
		}
		return valid;
	}

	public String getLocation() {
		return this.location;
	}

	private boolean setLocation(String location) {
		boolean valid = false;
		if (location != null && location.length() > 0) {
			valid = true;
			this.location = location;
		}
		return valid;
	}

	public Date getDate() {
		return this.date;
	}

	private boolean setDate(Date date) {
		boolean valid = false;
		if (date != null) {
			valid = true;
			this.date = date;
		}
		return valid;
	}

	public String getTime() {
		return this.time;
	}

	private boolean setTime(String time) {
		boolean valid = false;
		if (time != null && time.length() > 0) {
			valid = true;
			this.time = time;
		}
		return valid;
	}

}
