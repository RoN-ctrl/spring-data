package com.learn.springmvc.dao;

import com.learn.springmvc.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    List<Event> getAllByTitle(String title);

    List<Event> getAllByDate(Date day);

}
