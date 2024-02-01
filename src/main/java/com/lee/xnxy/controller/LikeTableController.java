package com.lee.xnxy.controller;

import com.lee.xnxy.converter.liketable.HasClickButtonBizRequestConverter;
import com.lee.xnxy.model.bizRequest.liketable.HasClickButtonBizRequest;
import com.lee.xnxy.model.request.liketable.*;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.LikeTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("like")
@Api("点赞模块")
public class LikeTableController {
    @Resource
    private LikeTableService likeTableService;

    @PostMapping("hasclick")
    @ApiOperation("判断用户是否点赞")
    public ResponseResult hasClickButton(@RequestBody @Valid HasClickButtonRequest hasClickButtonRequest) {
        HasClickButtonBizRequest hasClickButtonBizRequest = HasClickButtonBizRequestConverter.toHasClickButtonBizRequest(hasClickButtonRequest);
        return likeTableService.hasClickButton(hasClickButtonBizRequest);
    }

    @PostMapping("get-post-like-count")
    @ApiOperation("获取文章的点赞数量")
    public ResponseResult getPostLikeCount(@RequestBody @Valid GetPostLikeCountRequest getPostLikeCountRequest) {
        Long postId = Long.valueOf(getPostLikeCountRequest.getPostId());
        return likeTableService.getPostLikeCount(postId);
    }

    @PostMapping("click-post-like")
    @ApiOperation("帖子:点赞or取消")
    public ResponseResult clickPostLikeButton(@RequestBody @Valid ClickPostLikeButtonRequest clickPostLikeButtonRequest) {
        Long postId = Long.parseLong(clickPostLikeButtonRequest.getPostId());
        Long likeUserId = Long.parseLong(clickPostLikeButtonRequest.getLikeUserId());
        return likeTableService.clickPostLikeButton(postId, likeUserId);
    }

    @PostMapping("click-comment-like")
    @ApiOperation("评论:点赞or取消")
    public ResponseResult clickCommentLikeButton(@RequestBody @Valid ClickCommentLikeButtonRequest clickCommentLikeButtonRequest) {
        Long commentId = Long.parseLong(clickCommentLikeButtonRequest.getCommentId());
        Long likeUserId = Long.parseLong(clickCommentLikeButtonRequest.getLikeUserId());
        return likeTableService.clickCommentLikeButton(commentId, likeUserId);
    }
}
