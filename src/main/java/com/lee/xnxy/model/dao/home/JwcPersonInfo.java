package com.lee.xnxy.model.dao.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName xnxy_jwc_person_info
 */
@TableName(value ="xnxy_jwc_person_info")
@Data
public class JwcPersonInfo implements Serializable {
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
    @TableField(value = "password")
    private String password;

    /**
     * 
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 
     */
    @TableField(value = "school")
    private String school;

    /**
     * 
     */
    @TableField(value = "major")
    private String major;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JwcPersonInfo that = (JwcPersonInfo) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(jwcUsername, that.jwcUsername)) return false;
        if (!Objects.equals(password, that.password)) return false;
        if (!Objects.equals(realName, that.realName)) return false;
        if (!Objects.equals(gender, that.gender)) return false;
        if (!Objects.equals(school, that.school)) return false;
        return Objects.equals(major, that.major);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jwcUsername != null ? jwcUsername.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JwcPersonInfo{" +
                "id=" + id +
                ", jwcUsername='" + jwcUsername + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}