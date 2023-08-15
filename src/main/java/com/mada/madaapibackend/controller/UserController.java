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

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

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
