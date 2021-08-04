package com.sportBook.model;

// This object is created with the purpose of returning a JSON. The table does not get created in the database.
public class Score {

	private Integer id;

	private int score_A;

	private int score_B;

	public Score(Integer id, int score_A, int score_B) {
		setId(id);
		setScore_A(score_A);
		setScore_B(score_B);
	}

	public Score() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		if (id != null) {
			this.id = id;
		}
	}

	public int getScore_A() {
		return this.score_A;
	}

	public void setScore_A(Integer score_A) {
		if (score_A != null) {
			this.score_A = score_A;
		}
	}

	public int getScore_B() {
		return this.score_B;
	}

	public void setScore_B(Integer score_B) {
		if (score_B != null) {
			this.score_B = score_B;
		}
	}

}
