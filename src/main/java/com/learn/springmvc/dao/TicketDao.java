package com.learn.springmvc.dao;

import com.learn.springmvc.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Long> {

    List<Ticket> getAllByUserId(long id);

    List<Ticket> getAllByEventId(long id);

}
