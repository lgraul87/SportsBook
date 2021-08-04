package com.sportBook.repository;

/*
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.QueryHints;
*/
import org.springframework.data.jpa.repository.JpaRepository;

import com.sportBook.model.SportEvent;

public interface SportEventRepository extends JpaRepository<SportEvent, Integer> {

	/*
	 * I investigated a lot and found a solution to control the scenario when more users try to update the same sports event at the same time in the database and I
	 * think a good solution would be with Pessimistic Locking. I implemented it but it seems that it's not working
	 * 
	 * @Modifying
	 * 
	 * @Lock(value = LockModeType.PESSIMISTIC_WRITE)
	 * 
	 * @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value =
	 * "9000")}) <S extends SportEvent> S save(S entity);
	 */
}
