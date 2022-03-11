package com.yyh.crm.settings.service;

import com.yyh.crm.settings.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-03-08 19:35
 */
public interface UserService {

    User queryUserByLoginActAndPwd(Map<String,Object> map);

    public List<User> queryAllUsers();
}
