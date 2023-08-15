package com.mada.madaapibackend.common;

/**
 * 自定义响应工具类
 */
public class ResultUtils {

    /**
     * 成功
     * @param data 响应数据
     * @return 自定义响应体
     * @param <T> 响应数据类型
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(200,data,"OK");
    }

    /**
     * 错误
     * @param errorCode 自定义错误码
     * @return 自定义响应体
     * @param <T> 响应数据类型
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    /**
     * 错误
     * @param errorCode 自定义错误码
     * @param message 错误信息
     * @return 自定义响应体
     * @param <T> 响应数据类型
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode,String message){
        return new BaseResponse<>(errorCode,message);
    }

}
