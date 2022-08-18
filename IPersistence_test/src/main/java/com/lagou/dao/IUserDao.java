package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

public interface IUserDao {

    //查询所有用户
    List<User> findAll() throws Exception;


    //根据条件进行用户查询
    User findByCondition(User user) throws Exception;

    //  根据参数值进行插入操作
    boolean saveByParam( User user )throws Exception;

    //  根据ID值进行修改操作
    boolean updateCondition( User user )throws Exception;

    //  根据传入的条件值进行删除操作
    boolean removeByCondition ( User user )throws Exception;

}
