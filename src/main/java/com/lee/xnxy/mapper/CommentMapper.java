package com.lee.xnxy.mapper;

import com.lee.xnxy.model.dao.comment.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 晓龙coding
* @description 针对表【review】的数据库操作Mapper
* @createDate 2023-02-08 23:24:57
* @Entity com.lee.xnxydev.pojo.Review
*/
public interface CommentMapper extends BaseMapper<Comment> {
    void batchDeleteComment(List<Long> commentIdList);
}




