package com.learn.springmvc.service.impl;

import com.learn.springmvc.dao.EventDao;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.service.EventService;
import com.learn.springmvc.util.Utils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventDao eventDao;

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Event create(String title, Date date, double price) {
        return eventDao.save(new Event(title, date, price));
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
        Date formattedDate = Utils.getFormattedDate(day);
        return eventDao.getAllByDate(formattedDate);
    }

    @Override
    @Transactional
    public Event update(long id, String title, Date date) {
        Event event = getById(id);
        event.setTitle(title);
        event.setDate(date);
        return eventDao.save(event);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (!eventDao.existsById(id)) {
            return false;
        }
        eventDao.deleteById(id);
        return true;
    }
}
