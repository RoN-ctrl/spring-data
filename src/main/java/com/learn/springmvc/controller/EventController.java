package com.learn.springmvc.controller;

import com.learn.springmvc.facade.BookingFacade;
import com.learn.springmvc.model.Event;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.learn.springmvc.util.ControllerUtils.getModelAndView;

@Controller
@RequestMapping("/events")
public class EventController {

    public static final String EVENT_MODEL = "eventModel";
    public static final String EVENT_PAGE = "eventPage";
    private final BookingFacade bookingFacade;

    public EventController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping("/getById/{id}")
    public ModelAndView getEventById(@PathVariable long id) {

        var modelAndView = getModelAndView(EVENT_PAGE);
        var event = bookingFacade.getEventById(id);
        modelAndView.addObject(EVENT_MODEL, Objects.requireNonNullElseGet(event, () -> "Event not found : id=" + id));

        return modelAndView;
    }

    @GetMapping("/getByTitle/{title}")
    public ModelAndView getEventByTitle(@PathVariable String title,
                                        @RequestParam(required = false, defaultValue = "25") int pageSize,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {

        var modelAndView = getModelAndView(EVENT_PAGE);
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        modelAndView.addObject(EVENT_MODEL, events);
        return modelAndView;
    }

    @GetMapping("/getForDay/{day}")
    public ModelAndView getEventForDay(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date day,
                                       @RequestParam(required = false, defaultValue = "25") int pageSize,
                                       @RequestParam(required = false, defaultValue = "1") int pageNum) {

        var modelAndView = getModelAndView(EVENT_PAGE);
        List<Event> events = bookingFacade.getEventsForDay(day, pageSize, pageNum);
        modelAndView.addObject(EVENT_MODEL, events);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createEvent(@RequestParam String title,
                                    @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
                                    @RequestParam double price) {

        var modelAndView = getModelAndView(EVENT_PAGE);
        var event = bookingFacade.createEvent(title, date, price);
        modelAndView.addObject(EVENT_MODEL, "CREATED " + event);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateEvent(@PathVariable long id,
                                                  @RequestParam String title,
                                                  @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {

        var modelAndView = getModelAndView(EVENT_PAGE);
        var event = bookingFacade.updateEvent(id, title, date);
        modelAndView.addObject(EVENT_MODEL, "UPDATED " + event);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable long id) {

        boolean isDeleted = bookingFacade.deleteEvent(id);
        return isDeleted ? new ResponseEntity("DELETED", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
