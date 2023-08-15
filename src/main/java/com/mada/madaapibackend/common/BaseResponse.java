package com.mada.madaapibackend.common;

import lombok.Data;

/**
 * 自定义响应体
 * code 响应码
 * data 响应数据
 * message 响应信息
 * @param <T> 响应类型
 */
@Data
public class BaseResponse<T>{

    private static final long serialVersionUID = 3191241716373120793L;
    private int code;
    private T data;
    private String message;
    public BaseResponse(int code,T data,String message){
        this.code =code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
    }

    public BaseResponse(ErrorCode errorCode,String message){
        this.code = errorCode.getCode();
        this.data = null;
        this.message = message;
    }
}
