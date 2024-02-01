package com.lee.xnxy.service;

import com.lee.xnxy.model.bizRequest.posts.CreatePostsBizRequest;
import com.lee.xnxy.model.bizRequest.posts.ListPostsBizRequest;
import com.lee.xnxy.model.dao.posts.Posts;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.model.dto.ResponseResult;

/**
* @author 晓龙coding
* @description 针对表【life】的数据库操作Service
* @createDate 2023-02-02 22:05:10
*/
public interface PostsService extends IService<Posts> {
    /**
     * 展示商品信息
     * @return 返回商品信息
     */
    ResponseResult listPost(ListPostsBizRequest listPostsBizRequest);

    /**
     * 上传帖子并保存到数据库
     * @param createPostsBizRequest 上传的帖子信息
     * @return 上传成功或失败的提示
     */
    ResponseResult savePost(CreatePostsBizRequest createPostsBizRequest);

    /**
     * 通过帖子id删除帖子
     * @param pId 帖子id
     * @return 帖子删除成功或失败的提示
     */
    ResponseResult deletePostById(Long pId);

    ResponseResult viewPosts(Long postId);
}
