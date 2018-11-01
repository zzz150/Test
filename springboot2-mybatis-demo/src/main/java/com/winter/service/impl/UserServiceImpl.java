package com.winter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.dao.UserDao;
import com.winter.model.UserDomain;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:36
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDao userDao;//这里会报错，但是并不会影响

    @Override
    public int addUser(UserDomain user) {
        return userDao.insert(user);
    }

    @Override
    public int updateUser(UserDomain user) {
        return userDao.update(user);
    }

    @Override
    public int delUser(Integer id) {
        return userDao.del(id);
    }

    @Override
    public UserDomain selectOne(Integer id) {
        return userDao.selectOne(id);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

}
