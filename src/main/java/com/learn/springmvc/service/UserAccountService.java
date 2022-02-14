package com.learn.springmvc.service;

import com.learn.springmvc.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {

    UserAccount create(long userId, BigDecimal amount);

    UserAccount getById(long id);

    UserAccount getByUserId(long userId);

    UserAccount refillAmountOn(long id, BigDecimal amount);

    UserAccount payFor(long id, BigDecimal amount);

    boolean deleteById(long id);
}
