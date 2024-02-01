package com.lee.xnxy.service;

import com.lee.xnxy.model.bizRequest.comment.DeleteCommentBizRequest;
import com.lee.xnxy.model.bizRequest.comment.ListCommentBizRequest;
import com.lee.xnxy.model.bizRequest.comment.SaveCommentBizRequest;
import com.lee.xnxy.model.dao.comment.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.model.dto.ResponseResult;

/**
* @author 晓龙coding
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-02-08 23:24:57
*/
public interface CommentService extends IService<Comment> {

    ResponseResult listComment(ListCommentBizRequest listCommentBizRequest);

    ResponseResult saveReview(SaveCommentBizRequest saveCommentBizRequest);

    ResponseResult deleteCommentByPostId(Long postId);

    ResponseResult deleteComment(DeleteCommentBizRequest deleteCommentBizRequest);
}
