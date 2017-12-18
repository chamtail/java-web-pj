package edu.fudan.gomoku.service.impl;

import edu.fudan.gomoku.model.UserDO;
import edu.fudan.gomoku.repository.UserRepository;
import edu.fudan.gomoku.response.LoginResponse;
import edu.fudan.gomoku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(String userName, String password) {
        UserDO userDO = userRepository.queryUserByName(userName);
        LoginResponse loginResponse = new LoginResponse();
        final boolean success = userDO != null && Objects.equals(userDO.getPassword(), password);
        loginResponse.setSuccess(success);
        loginResponse.setMessage(success ? null : (userDO == null ? "User does not exist." : "Incorrect password."));
        return loginResponse;
    }

    @Override
    public Boolean register(String userName, String password) {
        return userRepository.insert(userName, password);
    }
}
