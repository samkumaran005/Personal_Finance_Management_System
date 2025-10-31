package com.finance.personalfinancemanager.service;

import com.finance.personalfinancemanager.model.User;
import com.finance.personalfinancemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User validateUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // valid user
        }
        return null; // invalid user
    }
}
