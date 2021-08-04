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
	@Column(updatable = false, insertable = false, name = "id", nullable = false, length = 2000)
	private Integer id;

	@Column(name = "name", nullable = false, length = 90)
	private String name;

	@Column(name = "scorea", nullable = false)
	private int scorea;

	@Column(name = "scoreb", nullable = false)
	private int scoreb;

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

	public SportEvent(Integer id, String name, Integer score_team_A, Integer score_team_B, String collaborators,
			String participants, String location, Date date, String time) {

		setId(id);
		setName(name);
		setScorea(score_team_A);
		setScoreb(score_team_B);
		setCollaborators(collaborators);
		setParticipants(participants);
		setLocation(location);
		setDate(date);
		setTime(time);
	}

	public SportEvent() {
	}

	public Integer getId() {
		return this.id;
	}

	private void setId(Integer id) {
		if (id != null && id > 0) {
			this.id = id;
		}
	}

	public String getName() {
		return this.name;
	}

	private void setName(String name) {
		if (name != null && name.length() > 0) {
			this.name = name;
		}
	}

	public int getScorea() {
		return scorea;
	}

	public void setScorea(int scorea) {
		this.scorea = scorea;
	}

	public int getScoreb() {
		return scoreb;
	}

	public void setScoreb(int scoreb) {
		this.scoreb = scoreb;
	}

	public String getCollaborators() {
		return this.collaborators;
	}

	private void setCollaborators(String collaborators) {
		if (collaborators != null && collaborators.length() > 0) {
			this.collaborators = collaborators;
		}
	}

	public String getParticipants() {
		return this.participants;
	}

	private void setParticipants(String participants) {
		if (participants != null && participants.length() > 0) {
			this.participants = participants;
		}
	}

	public String getLocation() {
		return this.location;
	}

	private void setLocation(String location) {
		if (location != null && location.length() > 0) {
			this.location = location;
		}
	}

	public Date getDate() {
		return this.date;
	}

	private void setDate(Date date) {
		if (date != null) {
			this.date = date;
		}
	}

	public String getTime() {
		return this.time;
	}

	private void setTime(String time) {
		if (time != null && time.length() > 0) {
			this.time = time;
		}
	}

}
