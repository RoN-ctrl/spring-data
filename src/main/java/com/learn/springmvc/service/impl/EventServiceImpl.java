package com.learn.springmvc.service.impl;

import com.learn.springmvc.dao.EventDao;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.service.EventService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    EventDao eventDao;

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @SneakyThrows
    @Override
    public Event create(String title, Date date) {
        return eventDao.save(new Event(title, date));
    }

    @Override
    public Event getById(long id) {
        return eventDao.getById(id);
    }

    @Override
    public List<Event> getByTitle(String title) {
        return eventDao.getAllByTitle(title);
    }

    @Override
    public List<Event> getByDay(Date day) {
        return eventDao.getAllByDate(day);
    }

    @Override
    public Event update(long id, String title, Date date) {
        Event event = getById(id);
        event.setTitle(title);
        event.setDate(date);
        return eventDao.save(event);
    }

    @Override
    public boolean deleteById(long id) {
        if (!eventDao.existsById(id)) {
            return false;
        }
        eventDao.deleteById(id);
        return true;
    }
}
