package com.sportBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportBook.model.SportEvent;

public interface SportEventRepository extends JpaRepository<SportEvent, Integer> {

}
