package com.mada.madaapibackend.controller;

import com.mada.madaapibackend.common.BaseResponse;
import com.mada.madaapibackend.common.ErrorCode;
import com.mada.madaapibackend.common.ResultUtils;
import com.mada.madaapibackend.exception.BusinessException;
import com.mada.madaapibackend.model.dto.user.UserLoginRequest;
import com.mada.madaapibackend.model.dto.user.UserRegisterRequest;
import com.mada.madaapibackend.model.entity.User;
import com.mada.madaapibackend.model.vo.UserVO;
import com.mada.madaapibackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    /**
     * 获取登录用户
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request){
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request){
        if(request == null){
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        boolean b = userService.useLogout(request);
        return ResultUtils.success(b);
    }
}
