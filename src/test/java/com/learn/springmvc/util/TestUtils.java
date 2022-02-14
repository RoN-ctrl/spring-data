package com.learn.springmvc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.springmvc.facade.BookingFacade;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.User;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class TestUtils {

    public static final String EVENT_TITLE = "Giselle";
    public static final String EVENT_DATE = "20-02-2021 18:00";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static final String USER_NAME = "Tommy";

    @SneakyThrows
    public static Event createTestEvent(BookingFacade bookingFacade) {
        return bookingFacade.createEvent(EVENT_TITLE, DATE_FORMAT.parse(EVENT_DATE));
    }

    @SneakyThrows
    public static User createTestUser(BookingFacade bookingFacade, String email) {
        return bookingFacade.createUser(USER_NAME, email);
    }

    public static <T> String getObjectAsString(final T obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
