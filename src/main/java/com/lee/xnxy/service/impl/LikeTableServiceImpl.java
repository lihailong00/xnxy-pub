package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.model.bizRequest.liketable.HasClickButtonBizRequest;
import com.lee.xnxy.model.dto.liketable.*;
import com.lee.xnxy.model.dao.liketable.LikeTable;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.LikeTableService;
import com.lee.xnxy.mapper.LikeTableMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 20882
* @description 针对表【xnxy_like_table】的数据库操作Service实现
* @createDate 2023-10-24 22:51:38
*/
@Service
public class LikeTableServiceImpl extends ServiceImpl<LikeTableMapper, LikeTable>
    implements LikeTableService{
    @Override
    public ResponseResult hasClickButton(HasClickButtonBizRequest hasClickButtonBizRequest) {
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LikeTable::getLikeUserId, hasClickButtonBizRequest.getLikeUserId())
                .eq(LikeTable::getPostId, hasClickButtonBizRequest.getPostId());
        List<LikeTable> likeTableList = this.list(lambdaQueryWrapper);
        HasClickButtonDTO hasClickButtonDTO = new HasClickButtonDTO();
        if (likeTableList.isEmpty()) {
            hasClickButtonDTO.setClickButton(false);
        } else {
            hasClickButtonDTO.setClickButton(true);
        }

        return ResponseResult.success(hasClickButtonDTO);
    }

    @Override
    public ResponseResult getPostLikeCount(Long postId) {
        Integer postLikeCount = getPostLikeCountNumber(postId);
        GetPostLikeCountDTO getPostLikeCountDTO = new GetPostLikeCountDTO();
        getPostLikeCountDTO.setLikeCount(postLikeCount);
        return ResponseResult.success(getPostLikeCountDTO);
    }

    @Override
    public ResponseResult clickPostLikeButton(Long postId, Long likeUserId) {
        // 查看是否点赞
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LikeTable::getLikeUserId, likeUserId)
                .eq(LikeTable::getPostId, postId);
        List<LikeTable> likeTableList = this.list(lambdaQueryWrapper);
        boolean liked = false;
        if (likeTableList.isEmpty()) {  // 该用户没有点赞帖子
            LikeTable likeTable = new LikeTable();
            likeTable.setPostId(postId);
            likeTable.setLikeUserId(likeUserId);
            this.save(likeTable);
            liked = true;
        } else {  // 该用户点赞了帖子
            this.remove(lambdaQueryWrapper);
        }
        // 获取帖子的点赞总数
        Integer postLikeCountNumber = getPostLikeCountNumber(postId);
        ClickPostLikeButtonDTO clickPostLikeButtonDTO = new ClickPostLikeButtonDTO();
        clickPostLikeButtonDTO.setLikeCount(postLikeCountNumber);
        clickPostLikeButtonDTO.setLiked(liked);
        return ResponseResult.success(clickPostLikeButtonDTO);
    }

    @Override
    public ResponseResult clickCommentLikeButton(Long commentId, Long likeUserId) {
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LikeTable::getLikeUserId, likeUserId)
                .eq(LikeTable::getCommentId, commentId);
        List<LikeTable> likeTableList = this.list(lambdaQueryWrapper);
        boolean liked = false;
        if (likeTableList.isEmpty()) {
            LikeTable likeTable = new LikeTable();
            likeTable.setCommentId(commentId);
            likeTable.setLikeUserId(likeUserId);
            this.save(likeTable);
            liked = true;
        } else {
            this.remove(lambdaQueryWrapper);
        }
        // 获取评论的点赞总数
        Integer commentLikeCountNumber = getCommentLikeCountNumber(commentId);
        ClickCommentLikeButtonDTO clickCommentLikeButtonDTO = new ClickCommentLikeButtonDTO();
        clickCommentLikeButtonDTO.setLikeCount(commentLikeCountNumber);
        clickCommentLikeButtonDTO.setLiked(liked);
        return ResponseResult.success(clickCommentLikeButtonDTO);
    }

    private Integer getPostLikeCountNumber(Long postId) {
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(LikeTable::getPostId);
        lambdaQueryWrapper.eq(LikeTable::getPostId, postId);
        List<LikeTable> likeTableList = this.list(lambdaQueryWrapper);

        return likeTableList.size();
    }

    private Integer getCommentLikeCountNumber(Long commentId) {
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(LikeTable::getCommentId);
        lambdaQueryWrapper.eq(LikeTable::getCommentId, commentId);
        List<LikeTable> likeTableList = this.list(lambdaQueryWrapper);

        return likeTableList.size();
    }
}




