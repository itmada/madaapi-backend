package com.mada.madaapibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mada.madaapibackend.model.entity.Interfaceinfo;

/**
* @author asjun
* @description 针对表【interfaceinfo(接口信息表)】的数据库操作Service
* @createDate 2023-07-31 17:16:49
*/
public interface InterfaceinfoService extends IService<Interfaceinfo> {
    void validInterfaceInfo(Interfaceinfo interfaceinfo,boolean add);
}
