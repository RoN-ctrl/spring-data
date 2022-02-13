package com.learn.springmvc.dao.impl;

import com.learn.springmvc.dao.Dao;
import com.learn.springmvc.exception.IdAlreadyExistsException;
import com.learn.springmvc.model.Event;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EventDao implements Dao<Event> {

    private final Map<Long, Event> events = new HashMap<>();

    @Override
    public Event save(Event event) throws IdAlreadyExistsException {
        if (events.containsKey(event.getId())) {
            throw new IdAlreadyExistsException("Event with id=" + event.getId() + " already exists");
        }
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public Optional<Event> getById(long id) {
        return Optional.ofNullable(events.get(id));
    }

    public List<Event> getByTitle(String title) {
        return events.values().stream()
                .filter(e -> Objects.equals(title, e.getTitle()))
                .collect(Collectors.toList());
    }

    public List<Event> getByDay(Date day) {
        return events.values().stream()
                .filter(e -> isDateDayEquals(day, e.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> update(Event event) {
        return Optional.ofNullable(events.replace(event.getId(), event));
    }

    @Override
    public boolean delete(long id) {
        if (!events.containsKey(id)) {
            return false;
        }
        events.remove(id);
        return !events.containsKey(id);
    }

    private boolean isDateDayEquals(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return Objects.equals(dateFormat.format(date1), dateFormat.format(date2));
    }
}
