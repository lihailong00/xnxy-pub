package com.lee.xnxy.converter.posts;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.posts.CreatePostsBizRequest;
import com.lee.xnxy.model.request.posts.CreatePostsRequest;
import com.lee.xnxy.util.UserContextDTOUtil;

import java.time.LocalDateTime;

public class CreatePostsBizRequestConverter {

    private CreatePostsBizRequestConverter() {

    }

    public static CreatePostsBizRequest toCreatePostsBizRequest(CreatePostsRequest createPostsRequest, String ip) {
        CreatePostsBizRequest createPostsBizRequest = new CreatePostsBizRequest();
        createPostsBizRequest.setUId(UserContextDTOUtil.getUserContextDTO().getUserId());
        createPostsBizRequest.setTitle(createPostsRequest.getTitle());
        createPostsBizRequest.setContent(createPostsRequest.getContent());
        createPostsBizRequest.setCreateTime(LocalDateTime.now());
        createPostsBizRequest.setHasDeleted(CommonConstant.NOT_DELETE);
        createPostsBizRequest.setIp(ip);
        return createPostsBizRequest;
    }
}
