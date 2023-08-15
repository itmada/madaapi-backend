package com.mada.madaapibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mada.madaapibackend.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asjun
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-07-28 20:39:07
* @Entity com.mada.madaapibackend.model.entity.User
*/

@Mapper
public interface UserMapper extends BaseMapper<User> {

}




