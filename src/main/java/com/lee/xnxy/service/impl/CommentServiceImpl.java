package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.mapper.CommentMapper;
import com.lee.xnxy.model.bizRequest.comment.DeleteCommentBizRequest;
import com.lee.xnxy.model.bizRequest.comment.ListCommentBizRequest;
import com.lee.xnxy.model.bizRequest.comment.SaveCommentBizRequest;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.dto.comment.ListCommentDTO;
import com.lee.xnxy.model.dao.comment.Comment;
import com.lee.xnxy.model.dao.liketable.LikeTable;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.CommentService;
import com.lee.xnxy.service.LikeTableService;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 晓龙coding
* @description 针对表【review】的数据库操作Service实现
* @createDate 2023-02-08 23:24:57
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

    @Resource
    private LikeTableService likeTableService;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @Override
    public ResponseResult listComment(ListCommentBizRequest listCommentBizRequest) {
        Long postId = listCommentBizRequest.getPostId();
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getPostId, postId).eq(Comment::getHasDeleted, CommonConstant.NOT_DELETE);
        List<Comment> commentList = this.list(queryWrapper);
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        // userId一定要在父线程中获取
        List<ListCommentDTO> commentDTOList = commentList.parallelStream().map(comment -> {
            // 查询评论发布者名称和头像
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getUId, comment.getUserId());
            User user = userService.getOne(userLambdaQueryWrapper);

            Integer commentLikeCount = getCommentLikeCount(comment.getCommentId());
            // 判断当前用户是否点赞了这个帖子
            Boolean hasClickLikeComment = hasClickLikeComment(comment.getCommentId(), userId);
            // 判断这条评论是否属于当前用户
            Boolean belongToMe = Objects.equals(comment.getUserId(), userId);
            return toListCommentDTO(comment, commentLikeCount, hasClickLikeComment, belongToMe, user.getUsername(), user.getPhotoImg());
        }).collect(Collectors.toList());
        return ResponseResult.success(commentDTOList);
    }


    @Override
    public ResponseResult saveReview(SaveCommentBizRequest saveCommentRequest) {
        Comment comment = toComment(saveCommentRequest);
        boolean success = this.save(comment);
        if (success) {
            return ResponseResult.success(CommonConstant.SEND_COMMENT_SUCCESS);
        }
        return ResponseResult.fail(CommonConstant.SEND_COMMENT_FAIL);
    }

    @Override
    public ResponseResult deleteCommentByPostId(Long postId) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getPostId, postId);
        lambdaQueryWrapper.select(Comment::getCommentId);
        List<Comment> commentList = this.list(lambdaQueryWrapper);
        List<Long> commentIdList = commentList.stream().map(Comment::getCommentId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(commentIdList)) {
            commentMapper.batchDeleteComment(commentIdList);
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult deleteComment(DeleteCommentBizRequest deleteCommentBizRequest) {
        List<Long> commentIdList = Collections.singletonList(deleteCommentBizRequest.getCommentId());
        commentMapper.batchDeleteComment(commentIdList);
        return ResponseResult.success();
    }

    private Comment toComment(SaveCommentBizRequest saveCommentBizRequest) {
        Comment comment = new Comment();
        comment.setUserId(saveCommentBizRequest.getUserId());
        comment.setPostId(saveCommentBizRequest.getPostId());
        comment.setContent(saveCommentBizRequest.getContent());
        comment.setCreateTime(LocalDateTime.now());
        comment.setIp(saveCommentBizRequest.getIp());
        comment.setHasDeleted(saveCommentBizRequest.getHasDeleted());
        return comment;
    }

    private Integer getCommentLikeCount(Long commentId) {
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LikeTable::getCommentId, commentId);
        lambdaQueryWrapper.select(LikeTable::getCommentId);
        List<LikeTable> likeTableList = likeTableService.list(lambdaQueryWrapper);
        return likeTableList.size();
    }

    private Boolean hasClickLikeComment(Long commentId, Long likeUserId) {
        LambdaQueryWrapper<LikeTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LikeTable::getCommentId, commentId)
                .eq(LikeTable::getLikeUserId, likeUserId);
        lambdaQueryWrapper.select(LikeTable::getCommentId);
        List<LikeTable> likeTableList = likeTableService.list(lambdaQueryWrapper);
        if (likeTableList.isEmpty()) {
            return false;
        }
        return true;
    }

    private ListCommentDTO toListCommentDTO(Comment comment, Integer commentLikeCount, Boolean hasClickLikeComment, Boolean belongToMe, String username, String userImage) {
        ListCommentDTO listCommentDTO = new ListCommentDTO();
        listCommentDTO.setCommentId(comment.getCommentId().toString());
        listCommentDTO.setUserId(comment.getUserId().toString());
        listCommentDTO.setContent(comment.getContent());
        listCommentDTO.setCreateTime(comment.getCreateTime());
        listCommentDTO.setLikeCount(commentLikeCount);
        listCommentDTO.setLiked(hasClickLikeComment);
        listCommentDTO.setBelongToMe(belongToMe);
        listCommentDTO.setUsername(username);
        listCommentDTO.setUserImage(userImage);
        return listCommentDTO;
    }
}




