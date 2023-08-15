package com.mada.madaapibackend.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void userRegister() {
        String account = "itmada123";
        String password = "12345678";
        String checkPassword = "12345678";
        userService.userRegister(account,password,checkPassword);
//        long result = userService.userRegister(account, password, checkPassword);
//        assert (result > 0);
//        account = "";
//        long l = userService.userRegister(account, password, checkPassword);
//        assert (l == -1);

        //用户名长度大于16
//        account = "itmada123itmada123234234234";
//        long l = userService.userRegister(account, password, checkPassword);
//        assert (l==-1);

        //用户名只能以字母或下划线开头
//        account = "12asdfasd";
//        long l = userService.userRegister(account, password, checkPassword);
//        assertEquals(-1,l,"l因该等于-1");

        //密码长度应大于8位
//        password = "123";
//        long l = userService.userRegister(account, password, checkPassword);
//        assertEquals(-1,l,"l因该等于-1");
//        //密码长度应小于16位
//        password = "1231232349273984729384234234";
//        long l2 = userService.userRegister(account, password, checkPassword);
//        assertEquals(-1,l2,"l因该等于-1");
//        //两次密码需要一致
//        password = "123456789";
//        long l3 = userService.userRegister(account, password, checkPassword);
//        assertEquals(-1,l3,"l因该等于-1");
//        //用户账户不能重复
//        password = "12345678";
//        account = "itmada123";
//        long l4 = userService.userRegister(account, password, checkPassword);
//        assertEquals(-1,l4,"l因该等于-1");
    }
}