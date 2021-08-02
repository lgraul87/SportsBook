package com.sportBook.model;

public class Score {

	private Integer id;

	private String score;

	public Score() {
	}

	public Score(int id, String score) {
		setId(id);
		setScore(score);
	}

	public int getId() {
		return this.id;
	}

	private void setId(Integer id) {
		if (id != null) {
			this.id = id;
		}
	}

	public String getScore() {
		return this.score;
	}

	private void setScore(String score) {
		if (score != null && score.length() > 0) {
			this.score = score;
		}
	}

}
