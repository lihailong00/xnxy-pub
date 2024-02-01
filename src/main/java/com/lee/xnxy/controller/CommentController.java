package com.lee.xnxy.controller;

import com.lee.xnxy.aop.LogAnnotation;
import com.lee.xnxy.converter.comment.DeleteCommentBizRequestConverter;
import com.lee.xnxy.converter.comment.ListCommentBizRequestConverter;
import com.lee.xnxy.converter.comment.SaveCommentBizRequestConverter;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.comment.DeleteCommentBizRequest;
import com.lee.xnxy.model.bizRequest.comment.ListCommentBizRequest;
import com.lee.xnxy.model.bizRequest.comment.SaveCommentBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.comment.DeleteCommentRequest;
import com.lee.xnxy.model.request.comment.ListCommentRequest;
import com.lee.xnxy.model.request.comment.SaveCommentRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.CommentService;
import com.lee.xnxy.util.IPUtil;
import com.lee.xnxy.util.UserContextDTOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 晓龙coding
 */
@RestController
@RequestMapping("comment")
@Api("评论模块")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping("list")
    @LogAnnotation(module="评论", operator="获取评论")
    @ApiOperation("显示所有评论（不分页）")
    public ResponseResult listComment(@RequestBody @Valid ListCommentRequest listCommentRequest) {
        ListCommentBizRequest listCommentBizRequest = ListCommentBizRequestConverter.toListCommentBizRequest(listCommentRequest);
        return commentService.listComment(listCommentBizRequest);
    }

    @PostMapping("create")
    @LogAnnotation(module="评论", operator="创建评论")
    @ApiOperation("保存评论")
    public ResponseResult saveComment(@RequestBody @Valid SaveCommentRequest saveCommentRequest) throws SysException {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        String ip = IPUtil.getIpAddr(httpServletRequest);
        SaveCommentBizRequest saveCommentBizRequest = SaveCommentBizRequestConverter.toSaveCommentBizRequest(saveCommentRequest, userId, ip);
        return commentService.saveReview(saveCommentBizRequest);
    }

    @PostMapping("delete")
    @ApiOperation("删除评论")
    public ResponseResult deleteComment(@RequestBody @Valid DeleteCommentRequest deleteCommentRequest) {
        DeleteCommentBizRequest deleteCommentBizRequest = DeleteCommentBizRequestConverter.toDeleteCommentBizRequest(deleteCommentRequest);
        return commentService.deleteComment(deleteCommentBizRequest);
    }
}
