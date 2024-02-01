package com.lee.xnxy.model.dao.comment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 
 * @TableName xnxy_comment
 */
@TableName(value ="xnxy_comment")
@Data
public class Comment implements Serializable {
    /**
     *
     */
    @TableId(value = "comment_id")
    private Long commentId;

    /**
     *
     */
    @TableField(value = "post_id")
    private Long postId;

    /**
     *
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     *
     */
    @TableField(value = "content")
    private String content;

    /**
     *
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     *
     */
    @TableField(value = "has_deleted")
    private Integer hasDeleted;

    /**
     *
     */
    @TableField(value = "ip")
    private String ip;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!Objects.equals(commentId, comment.commentId)) return false;
        if (!Objects.equals(postId, comment.postId)) return false;
        if (!Objects.equals(userId, comment.userId)) return false;
        if (!Objects.equals(content, comment.content)) return false;
        if (!Objects.equals(createTime, comment.createTime)) return false;
        if (!Objects.equals(hasDeleted, comment.hasDeleted)) return false;
        return Objects.equals(ip, comment.ip);
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (hasDeleted != null ? hasDeleted.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", hasDeleted=" + hasDeleted +
                ", ip='" + ip + '\'' +
                '}';
    }
}