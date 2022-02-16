package com.learn.springmvc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.springmvc.facade.BookingFacade;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.User;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class TestUtils {

    public static final String EVENT_TITLE = "Giselle";
    public static final String EVENT_DATE = "20-02-2021 18:00";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static final String USER_NAME = "Tommy";
    public static final double PRICE = 15;

    @SneakyThrows
    public static Event createTestEvent(BookingFacade bookingFacade) {
        return bookingFacade.createEvent(EVENT_TITLE, DATE_FORMAT.parse(EVENT_DATE), PRICE);
    }

    @SneakyThrows
    public static User createTestUser(BookingFacade bookingFacade, String email) {
        User user = bookingFacade.createUser(USER_NAME, email);
        bookingFacade.createUserAccount(user.getId(), 150);
        return user;
    }

    public static <T> String getObjectAsString(final T obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
