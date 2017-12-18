package edu.fudan.gomoku.controller;

import edu.fudan.gomoku.response.LoginResponse;
import edu.fudan.gomoku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public LoginResponse login(String userName, String password) {
        return userService.login(userName, password);
    }

    @RequestMapping("/register")
    public Boolean register(String userName, String password) {
        return userService.register(userName, password);
    }
}
