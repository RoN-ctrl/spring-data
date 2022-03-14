package com.learn.springmvc.service;

import com.learn.springmvc.model.UserAccount;

public interface UserAccountService {

    UserAccount create(long userId, double amount);

    UserAccount getById(long id);

    UserAccount getByUserId(long userId);

    UserAccount refillAmount(long id, double amount);

    UserAccount withdrawAmount(long id, double amount);

    boolean deleteById(long id);
}
