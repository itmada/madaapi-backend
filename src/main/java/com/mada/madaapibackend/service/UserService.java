package com.mada.madaapibackend.service;


import com.mada.madaapibackend.model.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author mada
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-07-28 20:39:07
*/

public interface UserService {
    /**
     * 用户注册
     * @param userAccount 账户
     * @param userPassword 密码
     * @param checkPassword 校验密码
     * @return 用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * 用户登录
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request 登录请求
     * @return 脱敏用户
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前用户
     * @param request 当前请求
     * @return 脱敏用户
     */
    User getCurrentUser(HttpServletRequest request);

    /**
     * 判断用户是否为管理员
     * @param request 单前请求
     * @return 是与否
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     * @param request 当前请求
     * @return ture
     */
    boolean useLogout(HttpServletRequest request );
}
