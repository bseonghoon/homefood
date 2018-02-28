package com.naver.homefood.dao;

import org.springframework.stereotype.Repository;

import com.naver.homefood.vo.User;

@Repository
public interface SignDao {
    void signUp(User user);

    User signIn(User user);

    int overlapCheck(String id);
}
