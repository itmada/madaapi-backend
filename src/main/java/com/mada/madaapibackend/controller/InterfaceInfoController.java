package com.mada.madaapibackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mada.madaapibackend.common.BaseResponse;
import com.mada.madaapibackend.common.ErrorCode;
import com.mada.madaapibackend.common.ResultUtils;
import com.mada.madaapibackend.constant.CommonSortConstant;
import com.mada.madaapibackend.exception.BusinessException;
import com.mada.madaapibackend.model.dto.interfaceInfo.InterfaceInfoDeleteRequest;
import com.mada.madaapibackend.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.mada.madaapibackend.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.mada.madaapibackend.model.entity.Interfaceinfo;
import com.mada.madaapibackend.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.mada.madaapibackend.model.entity.User;
import com.mada.madaapibackend.service.InterfaceinfoService;
import com.mada.madaapibackend.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口控制器
 */
@RestController
@RequestMapping("/interfaceInfo")
public class InterfaceInfoController {
    @Resource
    private InterfaceinfoService interfaceinfoService;

    @Resource
    private UserService userService;

    /**
     * 接口添加控制器
     * @param infoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest infoAddRequest, HttpServletRequest request) {
        if (infoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        Interfaceinfo interfaceinfo = new Interfaceinfo();
        BeanUtils.copyProperties(infoAddRequest, interfaceinfo);
        //校验
        interfaceinfoService.validInterfaceInfo(interfaceinfo, true);

        Long userId = userService.getLoginUser(request).getId();
        interfaceinfo.setUserId(userId);
        boolean result = interfaceinfoService.save(interfaceinfo);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(interfaceinfo.getId());
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest infoUpdateRequest,HttpServletRequest request){
        if(infoUpdateRequest == null || infoUpdateRequest.getId() < 0){
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        Interfaceinfo interfaceinfo = new Interfaceinfo();
        BeanUtils.copyProperties(infoUpdateRequest,interfaceinfo);
        //校验
        interfaceinfoService.validInterfaceInfo(interfaceinfo,false);
        User loginUser = userService.getLoginUser(request);
        Long id = infoUpdateRequest.getId();
        Interfaceinfo oldInterfaceInfo = interfaceinfoService.getById(id);
        //判断是否存在
        if(interfaceinfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //用户权限校验
        if(!userService.isAdmin(request) ) {
            if(!oldInterfaceInfo.getUserId().equals(loginUser.getId())){
                throw new BusinessException(ErrorCode.NOT_AUTH_ERROR);
            }
        }
        boolean result = interfaceinfoService.updateById(interfaceinfo);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody InterfaceInfoDeleteRequest infoDeleteRequest,HttpServletRequest request){
        if(infoDeleteRequest == null || infoDeleteRequest.getId() < 0){
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        //删除是否有效
        User loginUser = userService.getLoginUser(request);
        Long id = infoDeleteRequest.getId();
        Interfaceinfo oldInterfaceInfo = interfaceinfoService.getById(id);
        if(oldInterfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        /**
         * 权限认证 （ 管理员不能删除其他管理员的信息）
         */
        boolean isAdmin = userService.isAdmin(request);
        boolean userEquals = oldInterfaceInfo.getUserId().equals(loginUser.getId());
        if(!isAdmin) {
            if(!userEquals){
                throw new BusinessException(ErrorCode.NOT_AUTH_ERROR);
            }
        }
        Long interUserId = oldInterfaceInfo.getUserId();
        User interUser = userService.getById(interUserId);
        if(isAdmin && !userEquals && "admin".equals(interUser.getUserRole())){
            throw new BusinessException(ErrorCode.NOT_AUTH_ERROR);
        }
        boolean result = interfaceinfoService.removeById(id);
        return ResultUtils.success(result);
    }


    @GetMapping("/list/page")
    public BaseResponse<Page<Interfaceinfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest infoQueryRequest){
        if(infoQueryRequest== null){
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        Interfaceinfo interfaceinfo = new Interfaceinfo();
        BeanUtils.copyProperties(infoQueryRequest,interfaceinfo);
        int current = infoQueryRequest.getCurrent();
        int pageSize = infoQueryRequest.getPageSize();
        String description = infoQueryRequest.getDescription();
        String sortField = infoQueryRequest.getSortField();
        String sortOrder = infoQueryRequest.getSortOrder();

        //description 需支持模糊查询
        interfaceinfo.setDescription(null);
        if(pageSize > 50){
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        QueryWrapper<Interfaceinfo> queryWrapper= new QueryWrapper<>(interfaceinfo);
        queryWrapper.like(StringUtils.isNotBlank(description),"description",description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonSortConstant.ORDER_FOR_ASC),sortField);
        Page<Interfaceinfo> interfaceinfoPage = interfaceinfoService.page(new Page<>(current,pageSize), queryWrapper);
        return ResultUtils.success(interfaceinfoPage);
    }

}
