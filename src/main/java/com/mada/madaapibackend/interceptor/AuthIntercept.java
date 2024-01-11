package com.mada.madaapibackend.interceptor;

import com.mada.madaapibackend.annotation.AuthCheck;
import com.mada.madaapibackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class AuthIntercept {
    @Resource
    private UserService userService;


    /**
     * 执行拦截
     */
    @Around("@annotation(authCheck)")
    public Object doIntercept(ProceedingJoinPoint proceedingJoinPoint, AuthCheck authCheck){
        List<String> anyRole = Arrays.stream(authCheck.anyRole()).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        String mustRole = authCheck.mustRole();
        return null;
    }
}
