package com.sam.springbootcrud.controller;

import com.sam.springbootcrud.models.User;
import com.sam.springbootcrud.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@ComponentScan("service")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String printUsers(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users", params = "updateUserForm")
    public String updateUserForm(@RequestParam("updateUserForm") Long id, ModelMap model){
        model.addAttribute("user_id",id);
        System.out.println("user update "+id);
        return "update_user_form";
    }

    @GetMapping(value = "/users", params = "addUserForm")
    public String addUserForm(){
        return "add_user_form";
    }


    @PostMapping(value = "/users", params = "changeUser")
    public String changeUser(@RequestParam("changeUser") Long id, @ModelAttribute User user, ModelMap model) {
        user.setId(id);
        userService.updateUser(user);
        return printUsers(model);
    }

    @PostMapping(value = "/users", params = "deleteUser")
    public String deleteUser(@RequestParam("deleteUser") Long id, ModelMap model){
        userService.deleteUserById(id);
        return printUsers(model);
    }

    @PostMapping(value = "/users", params = "addUser")
    public String createUser(@ModelAttribute User user, ModelMap model){
        userService.createUser(user);
        return printUsers(model);
    }
}
