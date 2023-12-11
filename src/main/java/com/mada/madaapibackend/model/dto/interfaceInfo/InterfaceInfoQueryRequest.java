package com.mada.madaapibackend.model.dto.interfaceInfo;

import com.mada.madaapibackend.common.PageRequire;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoQueryRequest extends PageRequire implements Serializable {
    /**
     * 接口id
     */
    private Long id;

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 方法
     */
    private String method;

    /**
     * 状态 0-关闭，1-开启
     */
    private Integer status;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    private static final long serialVersionUID = -624408190048880461L;
}
