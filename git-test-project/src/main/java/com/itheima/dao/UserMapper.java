package com.itheima.dao;

import com.itheima.po.QueryVo;
import com.itheima.po.User;

import java.util.List;

//用户mapper接口
public interface UserMapper {
    //根据用户ID查询用户
    User findUserById(Integer id);

    //根据用户名模糊查询用户
    List<User> findUserByName(String username);

    //新增用户
    void insertUser(User user);

    //根据用户ID修改用户
    void updateUser(User user);

    //根据用户ID删除用户
    void deleteUser(Integer id);

    //使用pojo包装类型,根据用户名称模糊查询用户
    List<User> findUserByUserNameForPojo(QueryVo queryVo);

    //统计用户表中的用户数量
    Integer countUser();
}
