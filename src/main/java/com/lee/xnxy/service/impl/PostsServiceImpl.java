package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.mapper.LifeMapper;
import com.lee.xnxy.model.bizRequest.posts.CreatePostsBizRequest;
import com.lee.xnxy.model.bizRequest.posts.ListPostsBizRequest;
import com.lee.xnxy.model.dto.posts.ListPostsDTO;
import com.lee.xnxy.model.dao.comment.Comment;
import com.lee.xnxy.model.dao.liketable.LikeTable;
import com.lee.xnxy.model.dao.posts.Posts;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.CommentService;
import com.lee.xnxy.service.LikeTableService;
import com.lee.xnxy.service.PostsService;
import com.lee.xnxy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 晓龙coding
* @description 针对表【posts】的数据库操作Service实现
* @createDate 2023-02-02 22:05:10
*/
@Service
@Slf4j
public class PostsServiceImpl extends ServiceImpl<LifeMapper, Posts>
    implements PostsService {
    @Resource
    private UserService userService;
    @Resource
    private LifeMapper lifeMapper;
    @Resource
    private LikeTableService likeTableService;
    @Resource
    private CommentService commentService;


    @Override
    public ResponseResult listPost(ListPostsBizRequest listPostsBizRequest) {
        List<Posts> postList = lifeMapper.listPosts(listPostsBizRequest);
        List<ListPostsDTO> listPostsDTOS = postsList2DTO(postList);
        return ResponseResult.success(listPostsDTOS);
    }

    private List<ListPostsDTO> postsList2DTO(List<Posts> postList) {
        List<ListPostsDTO> listPostsDTOList = postList.stream().parallel().map(post -> {
            ListPostsDTO listPostsDTO = new ListPostsDTO();
            listPostsDTO.setPId(post.getPId().toString());
            listPostsDTO.setTitle(post.getTitle());
            listPostsDTO.setContent(post.getContent());
            listPostsDTO.setCreateTime(post.getCreateTime());
            listPostsDTO.setViewCount(post.getViewCount());

            // 通过uId查询author
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUId, post.getUId());
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                log.error(CommonConstant.SYSTEM_ERROR);
                listPostsDTO.setAuthor(CommonConstant.UNKNOWN_USER);
            } else {
                listPostsDTO.setAuthor(user.getUsername());
                listPostsDTO.setUserImage(user.getPhotoImg());
            }

            // 获取文章的点赞数
            Integer postLikeCount = getPostLikeCount(post.getPId());
            listPostsDTO.setLikeCount(postLikeCount);
            // 获取文章的评论数
            Integer postCommentCount = getPostCommentCount(post.getPId());
            listPostsDTO.setCommentCount(postCommentCount);

            return listPostsDTO;
        }).collect(Collectors.toList());

        return listPostsDTOList;
    }


    @Override
    public ResponseResult savePost(CreatePostsBizRequest createPostsBizRequest) {
        this.save(toPosts(createPostsBizRequest));
        return ResponseResult.success();
    }

    private Posts toPosts(CreatePostsBizRequest createPostsBizRequest) {
        Posts posts = new Posts();
        posts.setUId(createPostsBizRequest.getUId());
        posts.setTitle(createPostsBizRequest.getTitle());
        posts.setContent(createPostsBizRequest.getContent());
        posts.setCreateTime(createPostsBizRequest.getCreateTime());
        posts.setHasDeleted(createPostsBizRequest.getHasDeleted());
        posts.setIp(createPostsBizRequest.getIp());
        posts.setViewCount(0);
        return posts;
    }

    @Override
    public ResponseResult deletePostById(Long pId) {
        LambdaUpdateWrapper<Posts> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Posts::getPId, pId);
        updateWrapper.set(Posts::getHasDeleted, CommonConstant.DELETE);
        this.update(updateWrapper);
        // 删除帖子还会删除帖子下的评论
        commentService.deleteCommentByPostId(pId);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult viewPosts(Long postId) {
        lifeMapper.viewPosts(postId);
        return ResponseResult.success();
    }

    private Integer getPostLikeCount(Long postId) {
        LambdaUpdateWrapper<LikeTable> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(LikeTable::getPostId, postId);
        long count = likeTableService.count(lambdaUpdateWrapper);
        return Math.toIntExact(count);
    }

    private Integer getPostCommentCount(Long postId) {
        LambdaUpdateWrapper<Comment> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Comment::getPostId, postId).eq(Comment::getHasDeleted, CommonConstant.NOT_DELETE);
        long count = commentService.count(lambdaUpdateWrapper);
        return Math.toIntExact(count);
    }
}




