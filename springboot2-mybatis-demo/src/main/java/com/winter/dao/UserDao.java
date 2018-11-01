package com.winter.dao;

import com.winter.model.UserDomain;

import java.util.List;


public interface UserDao {

    int insert(UserDomain record);

    int update(UserDomain record);

    int del(Integer id);

    UserDomain selectOne(Integer id);

    List<UserDomain> selectUsers();
}
