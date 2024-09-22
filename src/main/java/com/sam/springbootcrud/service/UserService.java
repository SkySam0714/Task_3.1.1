package com.sam.springbootcrud.service;

import com.sam.springbootcrud.models.User;


import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(long id);
}
