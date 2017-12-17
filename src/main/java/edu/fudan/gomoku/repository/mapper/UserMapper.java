package edu.fudan.gomoku.repository.mapper;

import edu.fudan.gomoku.model.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert("insert into user (name, password) values (#{userName}, #{password})")
    void insert(@Param("userName") String userName, @Param("password") String password);

    @Select("select id, name, password from user where name = #{userName}")
    UserDO select(@Param("userName") String userName);
}
