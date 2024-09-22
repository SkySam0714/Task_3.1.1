package com.sam.springbootcrud.dao;



import com.sam.springbootcrud.models.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(long id);


}