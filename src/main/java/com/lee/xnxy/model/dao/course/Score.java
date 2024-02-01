package com.lee.xnxy.model.dao.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName xnxy_score
 */
@TableName(value ="xnxy_score")
@Data
public class Score implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(value = "xh")
    private Integer xh;

    /**
     *
     */
    @TableField(value = "kkxq")
    private String kkxq;

    /**
     *
     */
    @TableField(value = "kcbh")
    private String kcbh;

    /**
     *
     */
    @TableField(value = "kcmc")
    private String kcmc;

    /**
     *
     */
    @TableField(value = "cj")
    private BigDecimal cj;

    /**
     *
     */
    @TableField(value = "cjbs")
    private String cjbs;

    /**
     *
     */
    @TableField(value = "xf")
    private BigDecimal xf;

    /**
     *
     */
    @TableField(value = "zxs")
    private Integer zxs;

    /**
     *
     */
    @TableField(value = "khfs")
    private String khfs;

    /**
     *
     */
    @TableField(value = "kcsx")
    private String kcsx;

    /**
     *
     */
    @TableField(value = "kcxz")
    private String kcxz;

    /**
     *
     */
    @TableField(value = "jwc_username")
    private String jwcUsername;

    /**
     *
     */
    @TableField(value = "cjzw")
    private String cjzw;

    /**
     *
     */
    @TableField(value = "teacher_name")
    private String teacherName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (!Objects.equals(id, score.id)) return false;
        if (!Objects.equals(xh, score.xh)) return false;
        if (!Objects.equals(kkxq, score.kkxq)) return false;
        if (!Objects.equals(kcbh, score.kcbh)) return false;
        if (!Objects.equals(kcmc, score.kcmc)) return false;
        if (!Objects.equals(cj, score.cj)) return false;
        if (!Objects.equals(cjbs, score.cjbs)) return false;
        if (!Objects.equals(xf, score.xf)) return false;
        if (!Objects.equals(zxs, score.zxs)) return false;
        if (!Objects.equals(khfs, score.khfs)) return false;
        if (!Objects.equals(kcsx, score.kcsx)) return false;
        if (!Objects.equals(kcxz, score.kcxz)) return false;
        if (!Objects.equals(jwcUsername, score.jwcUsername)) return false;
        if (!Objects.equals(cjzw, score.cjzw)) return false;
        return Objects.equals(teacherName, score.teacherName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (xh != null ? xh.hashCode() : 0);
        result = 31 * result + (kkxq != null ? kkxq.hashCode() : 0);
        result = 31 * result + (kcbh != null ? kcbh.hashCode() : 0);
        result = 31 * result + (kcmc != null ? kcmc.hashCode() : 0);
        result = 31 * result + (cj != null ? cj.hashCode() : 0);
        result = 31 * result + (cjbs != null ? cjbs.hashCode() : 0);
        result = 31 * result + (xf != null ? xf.hashCode() : 0);
        result = 31 * result + (zxs != null ? zxs.hashCode() : 0);
        result = 31 * result + (khfs != null ? khfs.hashCode() : 0);
        result = 31 * result + (kcsx != null ? kcsx.hashCode() : 0);
        result = 31 * result + (kcxz != null ? kcxz.hashCode() : 0);
        result = 31 * result + (jwcUsername != null ? jwcUsername.hashCode() : 0);
        result = 31 * result + (cjzw != null ? cjzw.hashCode() : 0);
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", xh=" + xh +
                ", kkxq='" + kkxq + '\'' +
                ", kcbh='" + kcbh + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", cj=" + cj +
                ", cjbs='" + cjbs + '\'' +
                ", xf=" + xf +
                ", zxs=" + zxs +
                ", khfs='" + khfs + '\'' +
                ", kcsx='" + kcsx + '\'' +
                ", kcxz='" + kcxz + '\'' +
                ", jwcUsername='" + jwcUsername + '\'' +
                ", cjzw='" + cjzw + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}