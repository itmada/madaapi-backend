package com.mada.madaapibackend.common;


import com.mada.madaapibackend.constant.CommonSortConstant;
import lombok.Data;

@Data
public class PageRequire {
    /**
     * 起始页
     */
    private int current = 1;

    /**
     * 分页大小
     */
    private int pageSize = 10;


    /**
     * 分页字段
     */
    private String sortField;


    /**
     * 排序（默认升序）
     */
    private String sortOrder = CommonSortConstant.ORDER_FOR_ASC;
}
