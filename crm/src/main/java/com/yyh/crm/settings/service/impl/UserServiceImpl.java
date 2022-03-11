package com.yyh.crm.settings.service.impl;

import com.yyh.crm.settings.entity.User;
import com.yyh.crm.settings.mapper.UserMapper;
import com.yyh.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-03-08 19:36
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> queryAllUsers(){
        return userMapper.selectAllUsers(); 
    }
}
