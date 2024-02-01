package com.lee.xnxy.model.dao.home;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @author 晓龙coding
 * @TableName xnxy_user
 */
@TableName(value ="xnxy_user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(value = "u_id")
    private Long uId;

    /**
     *
     */
    @TableField(value = "username")
    private String username;

    /**
     * 教务处用户名
     */
    @TableField(value = "jwc_username")
    private String jwcUsername;

    /**
     * 教务处登录密码
     */
    @TableField(value = "jwc_password")
    private String jwcPassword;

    /**
     * 微信session_key
     */
    @TableField(value = "session_key")
    private String sessionKey;

    /**
     * 微信openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 头像URL
     */
    @TableField(value = "photo_img")
    private String photoImg;

    /**
     * 用户创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 用户是否注销，0表示未注销，1表示注销
     */
    @TableField(value = "has_deleted")
    private Integer hasDeleted;

    /**
     * 用户上次注销时间
     */
    @TableField(value = "delete_time")
    private LocalDateTime deleteTime;

    /**
     * 上一次登录教务处的时间
     */
    @TableField(value = "last_visit_jwc")
    private LocalDateTime lastVisitJwc;

    /**
     * 用户真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Integer gender;

    @TableField(value = "money")
    private Integer money;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(uId, user.uId)) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(jwcUsername, user.jwcUsername)) return false;
        if (!Objects.equals(jwcPassword, user.jwcPassword)) return false;
        if (!Objects.equals(sessionKey, user.sessionKey)) return false;
        if (!Objects.equals(openid, user.openid)) return false;
        if (!Objects.equals(photoImg, user.photoImg)) return false;
        if (!Objects.equals(createTime, user.createTime)) return false;
        if (!Objects.equals(hasDeleted, user.hasDeleted)) return false;
        if (!Objects.equals(deleteTime, user.deleteTime)) return false;
        if (!Objects.equals(lastVisitJwc, user.lastVisitJwc)) return false;
        if (!Objects.equals(realName, user.realName)) return false;
        if (!Objects.equals(gender, user.gender)) return false;
        return Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        int result = uId != null ? uId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (jwcUsername != null ? jwcUsername.hashCode() : 0);
        result = 31 * result + (jwcPassword != null ? jwcPassword.hashCode() : 0);
        result = 31 * result + (sessionKey != null ? sessionKey.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (photoImg != null ? photoImg.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (hasDeleted != null ? hasDeleted.hashCode() : 0);
        result = 31 * result + (deleteTime != null ? deleteTime.hashCode() : 0);
        result = 31 * result + (lastVisitJwc != null ? lastVisitJwc.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", username='" + username + '\'' +
                ", jwcUsername='" + jwcUsername + '\'' +
                ", jwcPassword='" + jwcPassword + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", openid='" + openid + '\'' +
                ", photoImg='" + photoImg + '\'' +
                ", createTime=" + createTime +
                ", hasDeleted=" + hasDeleted +
                ", deleteTime=" + deleteTime +
                ", lastVisitJwc=" + lastVisitJwc +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", money=" + money +
                '}';
    }
}