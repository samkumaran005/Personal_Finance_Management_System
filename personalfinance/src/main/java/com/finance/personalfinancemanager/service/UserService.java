package com.finance.personalfinancemanager.service;

import com.finance.personalfinancemanager.model.User;

public interface UserService {
    User validateUser(String username, String password);
}
