package com.learn.springmvc.controller;

import com.learn.springmvc.facade.BookingFacade;
import com.learn.springmvc.model.Event;
import com.learn.springmvc.model.Ticket;
import com.learn.springmvc.model.Ticket.Category;
import com.learn.springmvc.model.User;
import com.learn.springmvc.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookingFacade bookingFacade;

    @Test
    void bookTicketTest() throws Exception {
        mvc.perform(post("/tickets/newTicket?userId=1&eventId=1&place=17&category=BAR"))
                .andExpect(status().isOk());
    }

    @Test
    void getTicketsByUserTest() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com22");
        bookingFacade.bookTicket(user.getId(), 1, 19, Category.STANDARD);

        mvc.perform(get("/tickets/getByUser")
                .content(TestUtils.getObjectAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("ticketPage"))
                .andExpect(model().attributeExists("ticketModel"))
                .andExpect(model().attribute("ticketModel", bookingFacade.getBookedTickets(user, 10, 1)));
    }

    @Test
    void getTicketsByEvent() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com28");
        Event event = TestUtils.createTestEvent(bookingFacade);
        bookingFacade.bookTicket(user.getId(), event.getId(), 19, Category.STANDARD);

        mvc.perform(get("/tickets/getByEvent")
                .content(TestUtils.getObjectAsString(event))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("ticketPage"))
                .andExpect(model().attributeExists("ticketModel"))
                .andExpect(model().attribute("ticketModel", bookingFacade.getBookedTickets(event, 10, 1)));
    }

    @Test
    void cancelTicket() throws Exception {
        Ticket testTicket = bookingFacade.bookTicket(1, 1, 17, Category.BAR);

        mvc.perform(delete("/tickets/delete/{id}", testTicket.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }
}