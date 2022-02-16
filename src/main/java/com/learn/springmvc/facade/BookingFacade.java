package com.learn.springmvc.facade;

import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.Ticket;
import com.learn.springmvc.model.User;
import com.learn.springmvc.model.UserAccount;

import java.util.Date;
import java.util.List;

/**
 * Groups together all operations related to ticket booking.
 */
public interface BookingFacade {

    /**
     * Gets event by its id.
     *
     * @return Event.
     */
    Event getEventById(long eventId);

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach. In case nothing was found, empty
     * list is returned.
     *
     * @param title    Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    /**
     * Get list of events for specified day. In case nothing was found, empty list is returned.
     *
     * @param day      Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param title Event title.
     * @param date  Event date.
     * @param price Event price.
     * @return Created Event object.
     */
    Event createEvent(String title, Date date, double price);

    /**
     * Updates event using given data.
     *
     * @param id    Event id.
     * @param title Event title.
     * @param date  Event date.
     * @return Updated Event object.
     */
    Event updateEvent(long id, String title, Date date);

    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    boolean deleteEvent(long eventId);

    /**
     * Gets user by its id.
     *
     * @return User.
     */
    User getUserById(long userId);

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @return User.
     */
    User getUserByEmail(String email);

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach. In case nothing was found, empty
     * list is returned.
     *
     * @param name     Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    List<User> getUsersByName(String name, int pageSize, int pageNum);

    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param name  Username.
     * @param email User email.
     * @return Created User object.
     */
    User createUser(String name, String email);

    /**
     * Updates user using given data.
     *
     * @param id    User id.
     * @param name  Username.
     * @param email User email.
     * @return Updated User object.
     */
    User updateUser(long id, String name, String email);

    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    boolean deleteUser(long userId);

    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param userId   User Id.
     * @param eventId  Event Id.
     * @param place    Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws IllegalStateException if this place has already been booked.
     */
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     *
     * @param user     User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     *
     * @param event    Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    boolean cancelTicket(long ticketId);

    /**
     * Create UserAccount for a User.
     *
     * @param userId user id.
     * @param amount value to be added to current amount.
     * @return created UserAccount object.
     */
    UserAccount createUserAccount(long userId, double amount);

    /**
     * Get UserAccount with a specified id.
     *
     * @param id userAccount id.
     * @return UserAccount.
     */
    UserAccount getUserAccountById(long id);

    /**
     * Get UserAccount with a specified userId.
     *
     * @param userId user id.
     * @return UserAccount.
     */
    UserAccount getUserAccountByUserId(long userId);

    /**
     * Refill funds to a UserAccount with a specified id.
     *
     * @param id     userAccount id.
     * @param amount value to be added to current amount.
     * @return UserAccount with new amount.
     */
    UserAccount refillAmount(long id, double amount);

    /**
     * Withdraw funds from a UserAccount with a specified id.
     *
     * @param id     userAccount id.
     * @param amount value to be taken away from current amount.
     * @return UserAccount with new amount.
     */
    UserAccount withdrawAmount(long id, double amount);

    /**
     * Delete UserAccount with a specified id.
     *
     * @param id userAccount id.
     * @return Flag that shows whether userAccount has been deleted.
     */
    boolean deleteUserAccountById(long id);

}
