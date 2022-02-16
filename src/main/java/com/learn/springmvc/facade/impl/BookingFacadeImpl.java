package com.learn.springmvc.facade.impl;

import com.learn.springmvc.aspect.Loggable;
import com.learn.springmvc.facade.BookingFacade;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.Ticket;
import com.learn.springmvc.model.User;
import com.learn.springmvc.model.UserAccount;
import com.learn.springmvc.service.EventService;
import com.learn.springmvc.service.TicketService;
import com.learn.springmvc.service.UserAccountService;
import com.learn.springmvc.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;
    private final TicketService ticketService;
    private final EventService eventService;
    private final UserAccountService userAccountService;

    public BookingFacadeImpl(UserService userService, TicketService ticketService, EventService eventService,
                             UserAccountService userAccountService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.userAccountService = userAccountService;
    }

    @Override
    @Loggable
    public Event getEventById(long eventId) {
        return eventService.getById(eventId);
    }

    @Override
    @Loggable
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getByTitle(title);
    }

    @Override
    @Loggable
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getByDay(day);
    }

    @Override
    @Loggable
    public Event createEvent(String title, Date date, double price) {
        return eventService.create(title, date, price);
    }

    @Override
    @Loggable
    public Event updateEvent(long id, String title, Date date) {
        return eventService.update(id, title, date);
    }

    @Override
    @Loggable
    public boolean deleteEvent(long eventId) {
        return eventService.deleteById(eventId);
    }

    @Override
    @Loggable
    public User getUserById(long userId) {
        return userService.getById(userId);
    }

    @Override
    @Loggable
    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    @Loggable
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getByName(name);
    }

    @Override
    @Loggable
    public User createUser(String name, String email) {
        return userService.create(name, email);
    }

    @Override
    @Loggable
    public User updateUser(long id, String name, String email) {
        return userService.update(id, name, email);
    }

    @Override
    @Loggable
    public boolean deleteUser(long userId) {
        return userService.deleteById(userId);
    }

    @Override
    @Loggable
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        UserAccount userAccount = userAccountService.getByUserId(userId);
        double price = eventService.getById(eventId).getPrice();

        userAccountService.withdrawAmount(userAccount.getId(), price);
        return ticketService.create(userId, eventId, category, place);
    }

    @Override
    @Loggable
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getByUser(user);
    }

    @Override
    @Loggable
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getByEvent(event);
    }

    @Override
    @Loggable
    public boolean cancelTicket(long ticketId) {
        return ticketService.delete(ticketId);
    }

    @Override
    @Loggable
    public UserAccount createUserAccount(long userId, double amount) {
        return userAccountService.create(userId, amount);
    }

    @Override
    public UserAccount getUserAccountById(long id) {
        return userAccountService.getById(id);
    }

    @Override
    public UserAccount getUserAccountByUserId(long userId) {
        return userAccountService.getByUserId(userId);
    }

    @Override
    @Loggable
    public UserAccount refillAmount(long id, double amount) {
        return userAccountService.refillAmount(id, amount);
    }

    @Override
    public UserAccount withdrawAmount(long id, double amount) {
        return userAccountService.withdrawAmount(id, amount);
    }

    @Override
    public boolean deleteUserAccountById(long id) {
        return userAccountService.deleteById(id);
    }
}
