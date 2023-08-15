package com.mada.madaapibackend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mada.madaapibackend.exception.BusinessException;
import com.mada.madaapibackend.common.ErrorCode;
import com.mada.madaapibackend.mapper.UserMapper;
import com.mada.madaapibackend.model.entity.User;
import com.mada.madaapibackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.mada.madaapibackend.constant.UserConstant.USER_LOGIN_STATE;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /*
    固定盐值，加密密码
     */
    public static final String SALT = "itmada";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        //用户名，密码，校验码不为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名或密码为空");

        }

        //用户名长度大于4
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名长度应大于4");

        }

        //用户名长度需要小于16位
        if (userAccount.length() > 16) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名长度应小于16");

        }

        //用户名只能以字母或下划线开头
        String regex = "^[A-Za-z_].*";
        if (!userAccount.matches(regex)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名不合法");

        }

        //密码长度应大于8位
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "密码长度应大于8位");

        }

        //密码长度应小于16位
        if (userPassword.length() > 16) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "密码长度应小于16位");

        }

        //两次密码需要一致
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "两次密码不一致");

        }

        //用户账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "账号重复");

        }

        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT +userPassword).getBytes());

        //3.分配秘钥
        String accessKey = DigestUtils.md5DigestAsHex((SALT + userAccount + RandomStringUtils.random(5)).getBytes());
        String secretKey = DigestUtils.md5DigestAsHex((SALT + userAccount + RandomStringUtils.random(8)).getBytes());

        //4.插入用户数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setAccessKey(accessKey);
        user.setSecretKey(secretKey);
        int insert = userMapper.insert(user);
        if (insert < 1) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库系统异常");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        //用户名，密码，校验码不为空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名或密码为空");

        }

        //用户名长度大于4
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名长度应大于4");

        }

        //用户名长度需要小于16位
        if (userAccount.length() > 16) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名长度应小于16");

        }

        //用户名只能以字母或下划线开头
        String regex = "^[A-Za-z_].*";
        if (!userAccount.matches(regex)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "用户名不合法");

        }

        //密码长度应大于8位
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "密码长度应大于8位");

        }

        //密码长度应小于16位
        if (userPassword.length() > 16) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "密码长度应小于16位");

        }

        //2.对密码进行加密，并查询用户是否存在
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        System.out.println(userAccount+":"+encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("Login failed,userAccount or user Password is not matched");
            throw new BusinessException(ErrorCode.PARAM_ERROR,"用户名或密码错误");
        }
        //记录用户状态
        request.getSession().setAttribute(USER_LOGIN_STATE,user);

        //3.信息脱敏
        user.setUserPassword(null);
        return user;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean useLogout(HttpServletRequest request) {
        return false;
    }
}
