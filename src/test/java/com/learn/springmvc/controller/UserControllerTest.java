package com.learn.springmvc.controller;

import com.learn.springmvc.facade.BookingFacade;
import com.learn.springmvc.model.User;
import com.learn.springmvc.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    BookingFacade bookingFacade;

    @Test
    void getUserByIdTest() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com17");
        mvc.perform(get("/users/getById/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getUserById(user.getId())));
    }

    @Test
    void getUserByEmailTest() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com15");
        mvc.perform(get("/users/getByEmail/{email}", user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getUserByEmail(user.getEmail())));
    }

    @Test
    void getUserByNameTest() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com14");
        mvc.perform(get("/users/getByName/{name}", user.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getUsersByName(user.getName(), 10, 1)));
    }

    @Test
    void createUserTest() throws Exception {
        mvc.perform(post("/users/create?name=Billy&email=billy@mail.com"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserTest() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com11");
        mvc.perform(post("/users/update/" + user.getId() + "?name=Bill&email=bill@mail.com"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserTest() throws Exception {
        User user = TestUtils.createTestUser(bookingFacade, "test@mail.com12");
        mvc.perform(delete("/users/delete/{id}", user.getId()))
                .andExpect(status().isOk());
    }
}