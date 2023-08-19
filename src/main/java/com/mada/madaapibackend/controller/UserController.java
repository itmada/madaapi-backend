package com.mada.madaapibackend.controller;

import com.mada.madaapibackend.common.BaseResponse;
import com.mada.madaapibackend.common.ErrorCode;
import com.mada.madaapibackend.common.ResultUtils;
import com.mada.madaapibackend.exception.BusinessException;
import com.mada.madaapibackend.model.dto.UserLoginRequest;
import com.mada.madaapibackend.model.dto.UserRegisterRequest;
import com.mada.madaapibackend.model.entity.User;
import com.mada.madaapibackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 * @author itmada
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param requestBody 封装的请求
     * @return 自定义响应
     */
    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest requestBody) {
        if (requestBody == null) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        String userAccount = requestBody.getUserAccount();
        String userPassword = requestBody.getUserPassword();
        String checkPassword = requestBody.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }

        Long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }


    /**
     * 用户登录
     * @param loginRequest 封装的请求
     * @param request 当前请求
     * @return 封装的响应
     */
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {
        if (loginRequest == null) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }

        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

}
