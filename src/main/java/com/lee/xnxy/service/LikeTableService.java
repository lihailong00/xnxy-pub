package com.lee.xnxy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.model.bizRequest.liketable.HasClickButtonBizRequest;
import com.lee.xnxy.model.dao.liketable.LikeTable;
import com.lee.xnxy.model.dto.ResponseResult;

/**
* @author 20882
* @description 针对表【xnxy_like_table】的数据库操作Service
* @createDate 2023-10-24 22:51:38
*/
public interface LikeTableService extends IService<LikeTable> {

    ResponseResult hasClickButton(HasClickButtonBizRequest hasClickButtonBizRequest);

    ResponseResult getPostLikeCount(Long postId);

    ResponseResult clickPostLikeButton(Long postId, Long likeUserId);

    ResponseResult clickCommentLikeButton(Long commentId, Long likeUserId);
}
