package com.mada.madaapibackend.exception;

import com.mada.madaapibackend.common.ErrorCode;

public class BusinessException extends RuntimeException {
    private final int code;
    public BusinessException(int code,String message){
        super(message);
        this.code = code;
    }


    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode,String message){
        super(message);
        this.code = errorCode.getCode();
    }
}
