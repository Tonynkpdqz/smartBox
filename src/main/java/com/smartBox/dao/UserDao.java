package com.smartBox.dao;

import com.smartBox.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    void addUser(String username,String password,String authorities);
    User getUserByUsername(String username);
    void addAuth(String auth,String username);
}
