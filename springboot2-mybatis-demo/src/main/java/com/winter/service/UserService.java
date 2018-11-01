package com.winter.service;

import com.github.pagehelper.PageInfo;
import com.winter.model.UserDomain;


/**
 * @ClassName UserService
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:35
 * @Version 1.0
 */

public interface UserService {

    int addUser(UserDomain user);

    int updateUser(UserDomain user);

    int delUser(Integer id);

    UserDomain selectOne(Integer id);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

}
