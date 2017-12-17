package edu.fudan.gomoku.repository;

import edu.fudan.gomoku.repository.mapper.UserMapper;
import edu.fudan.gomoku.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserDO queryUserByName(String name) {
        return userMapper.select(name);
    }

    public Boolean insert(String userName, String password) {
        try {
            userMapper.insert(userName, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
