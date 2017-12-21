package com.smartBox.service;

import com.smartBox.dao.UserDao;
import com.smartBox.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao dao;

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean register(String username, String password,String authorities) {
        User user = dao.getUserByUsername(username);
        if (user == null){
            dao.addUser(username,password,authorities);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(String username) {
        return dao.getUserByUsername(username);
    }

    @Override
    public void addauth(String auth,String username) {
        dao.addAuth(auth,username);
    }
}
