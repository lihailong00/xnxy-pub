package com.lee.xnxy.model.dao.liketable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @TableName xnxy_like_table
 */
@TableName(value ="xnxy_like_table")
@Data
public class LikeTable implements Serializable {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "post_id")
    private Long postId;

    /**
     * 评论id
     */
    @TableField(value = "comment_id")
    private Long commentId;

    /**
     * 文章点赞者userid
     */
    @TableField(value = "like_user_id")
    private Long likeUserId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikeTable likeTable = (LikeTable) o;

        if (!Objects.equals(id, likeTable.id)) return false;
        if (!Objects.equals(postId, likeTable.postId)) return false;
        if (!Objects.equals(commentId, likeTable.commentId)) return false;
        return Objects.equals(likeUserId, likeTable.likeUserId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        result = 31 * result + (likeUserId != null ? likeUserId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LikeTable{" +
                "id=" + id +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", likeUserId=" + likeUserId +
                '}';
    }
}