package com.yyh.crm.settings.mapper;

import com.yyh.crm.settings.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据帐号和密码查询用户
     * @param map
     * @return
     */
    User selectUserByLoginActAndPwd(Map<String,Object> map);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUsers();
}