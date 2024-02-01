package com.lee.xnxy.validate;

import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.request.posts.DeletePostsRequest;
import com.lee.xnxy.model.request.posts.ListPostsRequest;
import com.lee.xnxy.model.dao.posts.Posts;
import com.lee.xnxy.service.PostsService;
import com.lee.xnxy.util.UserContextDTOUtil;
import com.lee.xnxy.validate.module.HasRightGetMyPostByUserIdModule;
import com.lee.xnxy.validate.module.HasRightOperateValidateModule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PostsControllerValidator {
    @Resource
    private HasRightOperateValidateModule hasRightOperateValidateModule;

    @Resource
    private HasRightGetMyPostByUserIdModule hasRightGetMyPostByUserIdModule;

    @Resource
    private PostsService postsService;
    public void deletePosts(DeletePostsRequest deletePostsRequest) {
        Long operatorUserId = UserContextDTOUtil.getUserContextDTO().getUserId();

        Long pId = Long.valueOf(deletePostsRequest.getPostId());
        Posts posts = postsService.getById(pId);
        if (posts != null) {
            Long belongUserId = posts.getUId();
            hasRightOperateValidateModule.validate(operatorUserId, belongUserId);
        } else {
            throw new ValidateException("文章信息不存在");
        }
    }

    public void listPost(ListPostsRequest listPostsRequest) {
        String userId = listPostsRequest.getUserId();
        hasRightGetMyPostByUserIdModule.validate(userId);
    }
}
