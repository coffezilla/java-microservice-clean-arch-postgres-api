package com.msleads.msleads.service;

import com.msleads.msleads.model.User;
import com.msleads.msleads.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User createUser(User user, String password) {
        return userRepository.createUser(user, password);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

}
