package com.jekamell.crud.forum.service;

import com.jekamell.crud.forum.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    User getUserByUserName(String userName);
}
