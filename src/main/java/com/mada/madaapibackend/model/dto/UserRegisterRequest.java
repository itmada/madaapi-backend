package com.mada.madaapibackend.model.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
