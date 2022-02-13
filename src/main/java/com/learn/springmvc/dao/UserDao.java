package com.learn.springmvc.dao;

import com.learn.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    List<User> getAllByName(String name);
}
