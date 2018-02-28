package com.naver.homefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.homefood.dao.SignDao;
import com.naver.homefood.vo.User;

@Service
public class SignService {

    @Autowired(required = false)
    private SignDao signDao;

    public int signUp(User user, String passwdConfirm) {

        if (user.getPasswd().equals(passwdConfirm)) {
            signDao.signUp(user);
            return 1;
        } else {
            return 0;
        }

    }

    public User signIn(User user) {
        return signDao.signIn(user);
    }

    public boolean overlapCheck(String id) {
        if (signDao.overlapCheck(id) == 0) {
            return true;
        }
        return false;
    }

}
