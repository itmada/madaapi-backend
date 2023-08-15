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
    public long userRegister(String userAccount,String userPassword,String checkPassword);
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request);
    public User getCurrentUser(HttpServletRequest request);
    public boolean isAdmin(HttpServletRequest request);
    public boolean useLogout(HttpServletRequest request );
}
