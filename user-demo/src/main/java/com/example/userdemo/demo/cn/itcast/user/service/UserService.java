package com.example.userdemo.demo.cn.itcast.user.service;

import com.example.userdemo.demo.cn.itcast.user.mapper.UserMapper;
import com.example.userdemo.demo.cn.itcast.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long id) {

        return userMapper.selectByPrimaryKey(id);
    }
}
