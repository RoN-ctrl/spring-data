package com.learn.springmvc.service.impl;

import com.learn.springmvc.dao.UserDao;
import com.learn.springmvc.model.User;
import com.learn.springmvc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User create(String name, String email) {
        return userDao.save(new User(name, email));
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getByName(String name) {
        return userDao.getAllByName(name);
    }

    @Override
    @Transactional
    public User update(long id, String name, String email) {
        User user = getById(id);
        user.setName(name);
        user.setEmail(email);
        return userDao.save(user);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (!userDao.existsById(id)) {
            return false;
        }
        userDao.deleteById(id);
        return true;
    }
}
