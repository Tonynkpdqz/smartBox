package com.smartBox.service;

import com.smartBox.domain.User;

public interface UserService {
    boolean register(String username,String password,String authorities);
    User getUser(String username);
    void addauth(String auth,String username);
}
