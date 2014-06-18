package com.jekamell.crud.forum.service;

import com.jekamell.crud.forum.model.User;
import com.jekamell.crud.forum.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao hibernateUserDao;

    @Autowired
    public UserServiceImpl(UserDao hibernateUserDao) {
        this.hibernateUserDao = hibernateUserDao;
    }

    @Override
    public void addUser(User user) {
        hibernateUserDao.addUser(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return hibernateUserDao.getUser(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("s:" + s);
        User user = getUserByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getRole().getCode()));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authority
        );
    }
}