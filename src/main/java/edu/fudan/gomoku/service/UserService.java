package edu.fudan.gomoku.service;

public interface UserService {

    Boolean login(String userName, String password);

    Boolean register(String userName, String password);
}
