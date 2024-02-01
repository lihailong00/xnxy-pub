package com.lee.xnxy.mapper;

import com.lee.xnxy.model.bizRequest.posts.ListPostsBizRequest;
import com.lee.xnxy.model.dao.posts.Posts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 晓龙coding
* @description 针对表【life】的数据库操作Mapper
* @createDate 2023-02-02 22:05:10
* @Entity com.lee.xnxydev.pojo.Life
*/
public interface LifeMapper extends BaseMapper<Posts> {
    List<Posts> listPosts(ListPostsBizRequest listPostsBizRequest);

    void viewPosts(Long postId);
}




