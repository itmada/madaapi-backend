package com.mada.madaapibackend.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 */
@Data
public class InterfaceInfoIdRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    private static final long serialVersionUID = 5698418815529692393L;
}
