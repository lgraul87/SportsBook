package com.sportBook.testing.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sportBook.model.SportEvent;
import com.sportBook.service.SportEventService;

@SpringBootTest
public class SportsBookApplicationTests {

	@Autowired
	private SportEventService sportEventService;

	final private int ID_SPORTEVENT_TO_ANALYSE = 1;

	final int INCREASE_UNITS = 1;
	final int INCREASE_SIMULTANEOUSLY_UNITS_A = 1;
	final int INCREASE_SIMULTANEOUSLY_UNITS_B = 1;

	final int DECREASE_UNITS = -1;
	final int DECREASE_SIMULTANEOUSLY_UNITS_A = -1;
	final int DECREASE_SIMULTANEOUSLY_UNITS_B = -1;

	@Test
	public void increaseScoreTeam_A_Testing() throws Exception {


		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_A(numericScore_A, numericScore_B, this.INCREASE_UNITS);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	@Test
	public void increaseScoreTeam_B_Testing() throws Exception {


		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_B(numericScore_A, numericScore_B, this.INCREASE_UNITS);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	@Test
	public void increaseScoreTeam_AB_Testing() throws Exception {


		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_AB(numericScore_A, numericScore_B, this.INCREASE_SIMULTANEOUSLY_UNITS_A,
				this.INCREASE_SIMULTANEOUSLY_UNITS_B);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	@Test
	public void decreaseScoreTeam_A_Testing() throws Exception {


		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_A(numericScore_A, numericScore_B, this.DECREASE_UNITS);

		responseAnalysisDecreaseScore(sportEvent, newUpdateScore, score);

	}

	@Test
	public void decreaseScoreTeam_B_Testing() throws Exception {


		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_B(numericScore_A, numericScore_B, this.DECREASE_UNITS);

		responseAnalysisDecreaseScore(sportEvent, newUpdateScore, score);

	}

	@Test
	public void decreaseScoreTeam_AB_Testing() throws Exception {


		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_AB(numericScore_A, numericScore_B, this.DECREASE_SIMULTANEOUSLY_UNITS_A,
				this.DECREASE_SIMULTANEOUSLY_UNITS_B);

		responseAnalysisDecreaseScore(sportEvent, newUpdateScore, score);

	}

	private int extractScore_A(String score) {
		char score_A = score.charAt(0);
		int numericScore_A = Character.getNumericValue(score_A);
		return numericScore_A;
	}

	private int extractScore_B(String score) {
		char score_B = score.charAt(score.length() - 1);
		int numericScore_B = Character.getNumericValue(score_B);
		return numericScore_B;
	}

	private String addUnitsToTeam_A(int numericScore_A, int numericScore_B, int units) {

		String newUpdateScore = (numericScore_A + units) + "-" + numericScore_B;
		return newUpdateScore;

	}

	private String addUnitsToTeam_B(int numericScore_A, int numericScore_B, int units) {

		String newUpdateScore = numericScore_A + "-" + (numericScore_B + units);
		return newUpdateScore;

	}

	private String addUnitsToTeam_AB(int numericScore_A, int numericScore_B, int simultaneouslyUnits_A,
			int simultaneouslyUnits_B) {

		String newUpdateScore = (numericScore_A + simultaneouslyUnits_A) + "-"
				+ (numericScore_B + simultaneouslyUnits_B);
		return newUpdateScore;

	}

	private void responseAnalysisIncreaseScore(SportEvent sportEvent, String newUpdateScore, String previusScore) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(), newUpdateScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventService.addSportEvent(sportEventUpdated);

		SportEvent QuerySportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		assertEquals(sportEventUpdated.getId(), QuerySportEvent.getId(),
				"Compare ID: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getCollaborators(), QuerySportEvent.getCollaborators(),
				"Compare collaborators: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getDate(), QuerySportEvent.getDate(),
				"Compare date: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getLocation(), QuerySportEvent.getLocation(),
				"Compare location: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getName(), QuerySportEvent.getName(),
				"Compare name: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getParticipants(), QuerySportEvent.getParticipants(),
				"Compare participants: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getScore(), QuerySportEvent.getScore(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(), previusScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventService.updateScoreSportEvent(sportEventUpdated, sportEvent.getId());

	}

	private void responseAnalysisDecreaseScore(SportEvent sportEvent, String newUpdateScore, String previusScore) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(), newUpdateScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventService.updateScoreSportEvent(sportEventUpdated, sportEventUpdated.getId());

		SportEvent QuerySportEvent = this.sportEventService.getSportEventById(ID_SPORTEVENT_TO_ANALYSE);

		assertEquals(sportEventUpdated.getId(), QuerySportEvent.getId(),
				"Compare ID: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getCollaborators(), QuerySportEvent.getCollaborators(),
				"Compare collaborators: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getDate(), QuerySportEvent.getDate(),
				"Compare date: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getLocation(), QuerySportEvent.getLocation(),
				"Compare location: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getName(), QuerySportEvent.getName(),
				"Compare name: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getParticipants(), QuerySportEvent.getParticipants(),
				"Compare participants: QuerySportEvent vs sportEventUpdated");
		assertNotEquals(sportEventUpdated.getScore(), QuerySportEvent.getScore(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(), previusScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventService.updateScoreSportEvent(QuerySportEvent, sportEventUpdated.getId());
	}

}
