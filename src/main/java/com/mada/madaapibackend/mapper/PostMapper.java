package com.mada.madaapibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mada.madaapibackend.model.entity.Post;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asjun
* @description 针对表【post(帖子)】的数据库操作Mapper
* @createDate 2023-07-31 17:16:49
* @Entity com.mada.madaapibackend.model.entity.Post
*/

@Mapper
public interface PostMapper extends BaseMapper<Post> {

}




