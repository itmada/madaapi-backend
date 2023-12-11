package com.mada.madaapibackend.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mada.madaapibackend.common.ErrorCode;
import com.mada.madaapibackend.exception.BusinessException;
import com.mada.madaapibackend.mapper.InterfaceinfoMapper;
import com.mada.madaapibackend.model.entity.Interfaceinfo;
import com.mada.madaapibackend.service.InterfaceinfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author asjun
 * @description 针对表【interfaceinfo(接口信息表)】的数据库操作Service实现
 * @createDate 2023-07-31 17:16:49
 */
@Service
public class InterfaceinfoServiceImpl extends ServiceImpl<InterfaceinfoMapper, Interfaceinfo> implements InterfaceinfoService {

    /**
     * 接口信息校验
     *
     * @param interfaceinfo
     * @param add
     */
    @Override
    public void validInterfaceInfo(Interfaceinfo interfaceinfo, boolean add) {
        if (interfaceinfo == null) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        String name = interfaceinfo.getName();
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAM_ERROR);

            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "名称过长");
        }
    }
}




