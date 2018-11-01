package com.winter.model;

import java.util.Date;

/**
 * @ClassName UserDomain
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 11:32
 * @Version 1.0
 */

public class UserDomain {

    private Integer userId;

    private String userName;

    private String password;

    private String phone;

    private Date addTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
