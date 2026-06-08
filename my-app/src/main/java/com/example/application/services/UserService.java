package com.example.application.services;

import com.example.application.data.User;
import com.example.application.data.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String login, String password) {

        if (login == null || login.isBlank()
                || password == null || password.isBlank()) {
            return false;
        }

        if (userRepository.findByLogin(login).isPresent()) {
            return false;
        }

        userRepository.save(new User(login, password));

        return true;
    }

    public boolean login(String login, String password) {

        return userRepository.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}