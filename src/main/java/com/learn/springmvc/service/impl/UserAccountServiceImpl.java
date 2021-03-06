package com.learn.springmvc.service.impl;

import com.learn.springmvc.dao.UserAccountDao;
import com.learn.springmvc.exception.InsufficientFundsException;
import com.learn.springmvc.model.UserAccount;
import com.learn.springmvc.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountDao userAccountDao;

    public UserAccountServiceImpl(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    @Transactional
    public UserAccount create(long userId, double amount) {
        return userAccountDao.save(new UserAccount(userId, amount));
    }

    @Override
    public UserAccount getById(long id) {
        return userAccountDao.getById(id);
    }

    @Override
    public UserAccount getByUserId(long userId) {
        return userAccountDao.getByUserId(userId);
    }

    @Override
    @Transactional
    public UserAccount refillAmount(long id, double amount) {
        UserAccount userAccount = getById(id);
        userAccount.setAmount(userAccount.getAmount() + (amount));
        return userAccountDao.save(userAccount);
    }

    @Override
    @Transactional(rollbackFor = InsufficientFundsException.class)
    public UserAccount withdrawAmount(long id, double amount) {
        UserAccount userAccount = getById(id);
        if (userAccount.getAmount() - amount < 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        userAccount.setAmount(userAccount.getAmount() - amount);
        return userAccountDao.save(userAccount);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (!userAccountDao.existsById(id)) {
            return false;
        }
        userAccountDao.deleteById(id);
        return true;
    }
}
