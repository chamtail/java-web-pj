package edu.fudan.gomoku.service;

import edu.fudan.gomoku.response.LoginResponse;

public interface UserService {

    LoginResponse login(String userName, String password);

    Boolean register(String userName, String password);
}
