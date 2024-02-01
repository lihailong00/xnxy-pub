package com.lee.xnxy.model.dao.posts;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName life
 */
@TableName(value ="life")
@Data
public class Posts implements Serializable {
    /**
     * 
     */
    @TableId(value = "p_id")
    private Long pId;

    /**
     * 
     */
    @TableField(value = "u_id")
    private Long uId;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章内容
     */
    @TableField(value = "content")
    private String content;


    /**
     * 文章发布时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 文章是否被删除，0表示未删除，1表示删除
     */
    @TableField(value = "has_deleted")
    private Integer hasDeleted;

    /**
     * 发布IP
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 查看次数
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posts posts = (Posts) o;

        if (!Objects.equals(pId, posts.pId)) return false;
        if (!Objects.equals(uId, posts.uId)) return false;
        if (!Objects.equals(title, posts.title)) return false;
        if (!Objects.equals(content, posts.content)) return false;
        if (!Objects.equals(createTime, posts.createTime)) return false;
        if (!Objects.equals(hasDeleted, posts.hasDeleted)) return false;
        if (!Objects.equals(ip, posts.ip)) return false;
        return Objects.equals(viewCount, posts.viewCount);
    }

    @Override
    public int hashCode() {
        int result = pId != null ? pId.hashCode() : 0;
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (hasDeleted != null ? hasDeleted.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "pId=" + pId +
                ", uId=" + uId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", hasDeleted=" + hasDeleted +
                ", ip='" + ip + '\'' +
                ", viewCount=" + viewCount +
                '}';
    }
}