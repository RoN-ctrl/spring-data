package com.learn.springmvc.dao.impl;

import com.learn.springmvc.dao.Dao;
import com.learn.springmvc.exception.IdAlreadyExistsException;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.Ticket;
import com.learn.springmvc.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TicketDao implements Dao<Ticket> {

    private final Map<Long, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket save(Ticket ticket) throws IdAlreadyExistsException {
        if (tickets.containsKey(ticket.getId())) {
            throw new IdAlreadyExistsException("Ticket with id=" + ticket.getId() + " already exists");
        }
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> getById(long id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public List<Ticket> getByUser(User user) {
        return tickets.values().stream()
                .filter(t -> Objects.equals(user.getId(), t.getUserId()))
                .collect(Collectors.toList());
    }

    public List<Ticket> getByEvent(Event event) {
        return tickets.values().stream()
                .filter(t -> Objects.equals(event.getId(), t.getEventId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> update(Ticket ticket) {
        return Optional.ofNullable(tickets.replace(ticket.getId(), ticket));
    }

    @Override
    public boolean delete(long id) {
        if (!tickets.containsKey(id)) {
            return false;
        }
        tickets.remove(id);
        return !tickets.containsKey(id);
    }
}
