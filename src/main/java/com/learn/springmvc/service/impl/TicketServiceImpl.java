package com.learn.springmvc.service.impl;

import com.learn.springmvc.dao.TicketDao;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.Ticket;
import com.learn.springmvc.model.User;
import com.learn.springmvc.service.TicketService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @SneakyThrows
    @Override
    public Ticket create(long userId, long eventId, Ticket.Category category, int place) {
        return ticketDao.save(new Ticket(eventId, userId, category, place));
    }

    @Override
    public List<Ticket> getByUser(User user) {
        return ticketDao.getAllByUserId(user.getId());
    }

    @Override
    public List<Ticket> getByEvent(Event event) {
        return ticketDao.getAllByEventId(event.getId());
    }

    @Override
    public boolean delete(long id) {
        if (!ticketDao.existsById(id)) {
            return false;
        }
        ticketDao.deleteById(id);
        return true;
    }
}
