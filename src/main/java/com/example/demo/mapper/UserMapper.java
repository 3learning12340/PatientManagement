package com.example.demo.mapper;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.FileOwn;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-11-07 14:08
 */
@Repository
@Mapper
public interface UserMapper {
    List<User> queryUsers(User user);
    List<User> queryAllUsers();
//    User queryUser(Integer id);
    User queryUser(User user);
    User queryUserById(Integer id);
    List<User> queryUserByPhone(User user);
    List<User> queryUserByUsername(User user);
    List<User> queryUserByNickName(User user);
    int deleteUserById(Integer id);
    int deleteUserByUsername(String username);
    int addUser(User user);
    int updateUser(User user);
}

