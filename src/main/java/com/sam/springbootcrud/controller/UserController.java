package com.sam.springbootcrud.controller;

import com.sam.springbootcrud.models.User;
import com.sam.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@ComponentScan("service")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String printUsers(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users/update/{id}")
    public String updateUserAdminForm(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user_id",id);
        return "update_user_form";
    }

    @GetMapping(value = "/users/create")
    public String addUserAdminForm(){
        return "add_user_form";
    }


    @PostMapping(value = "/users/update/{id}")
    public String changeUser(@PathVariable("id") Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/create")
    public String createUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/users";
    }
}
