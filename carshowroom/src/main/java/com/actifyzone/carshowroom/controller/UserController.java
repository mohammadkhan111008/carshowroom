package com.actifyzone.carshowroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.actifyzone.carshowroom.entity.User;
import com.actifyzone.carshowroom.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        User u = repo.findByUsernameAndPassword(
                user.username,
                user.password);

        if(u!=null)
        {
            return "Login Successful";
        }
        else
        {
            return "Invalid Username or Password";
        }
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return repo.save(user);

    }
}