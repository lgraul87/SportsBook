package com.sportBook.testing.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sportBook.controller.SportEventController;
import com.sportBook.model.SportEvent;
import com.sportBook.service.SportEventService;

@SpringBootTest
public class RepositoryTest {

	@Autowired
	SportEventService sportEventService;

	@Autowired
	SportEventController sportEventController;

	@Test
	public void getSportsEventsTest() throws Exception {
		initDB();
		sportEventController.getSportsEvents();

	}

	@Test
	public Integer getSportEventByIdTest() throws Exception {
		SportEvent sportEvent = sportEventController
				.getSportEventById(sportEventService.getSportsEvents().get(0).getId());
		return sportEvent.getId();

	}

	@Test
	public Integer getScoreOfSportEventTest() throws Exception {

		int id = getSportEventByIdTest();
		sportEventController.getScoreOfSportEvent(1);
		return id;

	}

	@Test
	public void addSportEventTest() throws Exception {

		sportEventController.addSportEvent(
				sportEventController.getSportEventById(sportEventService.getSportsEvents().get(0).getId()));

	}

	@Test
	public void updateSportEventTest() throws Exception {
		SportEvent sportEvent = sportEventController
				.getSportEventById(sportEventService.getSportsEvents().get(0).getId());
		sportEventController.updateSportEvent(sportEvent.getId(), sportEvent);
		sportEventController.deleteSportEvent(sportEvent.getId());

	}

	@Test
	public void deleteSportEventTest() throws Exception {
		SportEvent sportEvent = new SportEvent(1, "Team A vs Team B", "1-0", "Team A", "participants", "Madrid",
				new Date(), "17:00pm to 18:30pm ");
		sportEventController.addSportEvent(sportEvent);
		int id = getSportEventByIdTest();
		sportEventController.deleteSportEvent(id);
		initDB();

	}

	public void initDB() throws Exception {
		if (sportEventService.getSportsEvents().size() < 1) {
			SportEvent sportEvent = new SportEvent(1, "Team A vs Team B", "1-0", "Team A", "participants", "Madrid",
					new Date(), "17:00pm to 18:30pm ");
			sportEventController.addSportEvent(sportEvent);
		}
	}
}
