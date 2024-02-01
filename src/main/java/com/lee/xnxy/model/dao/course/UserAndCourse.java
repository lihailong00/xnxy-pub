package com.lee.xnxy.model.dao.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName xnxy_user_and_course
 */
@TableName(value ="xnxy_user_and_course")
@Data
public class UserAndCourse implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "jwc_username")
    private String jwcUsername;

    /**
     * 
     */
    @TableField(value = "kcbh")
    private String kcbh;

    /**
     * 
     */
    @TableField(value = "kxh")
    private Integer kxh;

    @TableField(value = "kkxq")
    private String kkxq;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAndCourse that = (UserAndCourse) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(jwcUsername, that.jwcUsername)) return false;
        if (!Objects.equals(kcbh, that.kcbh)) return false;
        if (!Objects.equals(kxh, that.kxh)) return false;
        return Objects.equals(kkxq, that.kkxq);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jwcUsername != null ? jwcUsername.hashCode() : 0);
        result = 31 * result + (kcbh != null ? kcbh.hashCode() : 0);
        result = 31 * result + (kxh != null ? kxh.hashCode() : 0);
        result = 31 * result + (kkxq != null ? kkxq.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserAndCourse{" +
                "id=" + id +
                ", jwcUsername='" + jwcUsername + '\'' +
                ", kcbh='" + kcbh + '\'' +
                ", kxh=" + kxh +
                ", kkxq='" + kkxq + '\'' +
                '}';
    }
}