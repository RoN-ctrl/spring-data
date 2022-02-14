package com.learn.springmvc.dao;

import com.learn.springmvc.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

    UserAccount getByUserId(long userId);

}
