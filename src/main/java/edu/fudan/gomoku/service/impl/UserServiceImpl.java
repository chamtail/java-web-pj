package edu.fudan.gomoku.service.impl;

import edu.fudan.gomoku.model.UserDO;
import edu.fudan.gomoku.repository.UserRepository;
import edu.fudan.gomoku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean login(String userName, String password) {
        UserDO userDO = userRepository.queryUserByName(userName);
        return userDO != null && Objects.equals(userDO.getPassword(), password);
    }

    @Override
    public Boolean register(String userName, String password) {
        return userRepository.insert(userName, password);
    }
}
