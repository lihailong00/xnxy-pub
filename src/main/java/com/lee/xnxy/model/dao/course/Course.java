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
 * @TableName xnxy_course
 */
@TableName(value ="xnxy_course")
@Data
public class Course implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 
     */
    @TableField(value = "kcmc")
    private String kcmc;

    /**
     * 
     */
    @TableField(value = "xf")
    private BigDecimal xf;

    /**
     * 
     */
    @TableField(value = "kcsx")
    private String kcsx;

    /**
     * 
     */
    @TableField(value = "xkjd")
    private String xkjd;

    @TableField(value = "kkxq")
    private String kkxq;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!Objects.equals(id, course.id)) return false;
        if (!Objects.equals(kcbh, course.kcbh)) return false;
        if (!Objects.equals(kxh, course.kxh)) return false;
        if (!Objects.equals(kcmc, course.kcmc)) return false;
        if (!Objects.equals(xf, course.xf)) return false;
        if (!Objects.equals(kcsx, course.kcsx)) return false;
        if (!Objects.equals(xkjd, course.xkjd)) return false;
        return Objects.equals(kkxq, course.kkxq);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (kcbh != null ? kcbh.hashCode() : 0);
        result = 31 * result + (kxh != null ? kxh.hashCode() : 0);
        result = 31 * result + (kcmc != null ? kcmc.hashCode() : 0);
        result = 31 * result + (xf != null ? xf.hashCode() : 0);
        result = 31 * result + (kcsx != null ? kcsx.hashCode() : 0);
        result = 31 * result + (xkjd != null ? xkjd.hashCode() : 0);
        result = 31 * result + (kkxq != null ? kkxq.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", kcbh='" + kcbh + '\'' +
                ", kxh=" + kxh +
                ", kcmc='" + kcmc + '\'' +
                ", xf=" + xf +
                ", kcsx='" + kcsx + '\'' +
                ", xkjd='" + xkjd + '\'' +
                ", kkxq='" + kkxq + '\'' +
                '}';
    }
}