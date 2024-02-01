package com.lee.xnxy.model.dao.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName xnxy_course_and_time_with_place
 */
@TableName(value ="xnxy_course_and_time_with_place")
@Data
public class CourseAndTimeWithPlace implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "kcmc")
    private String kcmc;

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
    @TableField(value = "week")
    private Integer week;

    /**
     * 
     */
    @TableField(value = "day")
    private Integer day;

    /**
     * 
     */
    @TableField(value = "start_section")
    private Integer startSection;

    /**
     * 
     */
    @TableField(value = "end_section")
    private Integer endSection;

    /**
     * 
     */
    @TableField(value = "place")
    private String place;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CourseAndTimeWithPlace other = (CourseAndTimeWithPlace) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getKcmc() == null ? other.getKcmc() == null : this.getKcmc().equals(other.getKcmc()))
            && (this.getKcbh() == null ? other.getKcbh() == null : this.getKcbh().equals(other.getKcbh()))
            && (this.getKxh() == null ? other.getKxh() == null : this.getKxh().equals(other.getKxh()))
            && (this.getWeek() == null ? other.getWeek() == null : this.getWeek().equals(other.getWeek()))
            && (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getStartSection() == null ? other.getStartSection() == null : this.getStartSection().equals(other.getStartSection()))
            && (this.getEndSection() == null ? other.getEndSection() == null : this.getEndSection().equals(other.getEndSection()))
            && (this.getPlace() == null ? other.getPlace() == null : this.getPlace().equals(other.getPlace()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKcmc() == null) ? 0 : getKcmc().hashCode());
        result = prime * result + ((getKcbh() == null) ? 0 : getKcbh().hashCode());
        result = prime * result + ((getKxh() == null) ? 0 : getKxh().hashCode());
        result = prime * result + ((getWeek() == null) ? 0 : getWeek().hashCode());
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getStartSection() == null) ? 0 : getStartSection().hashCode());
        result = prime * result + ((getEndSection() == null) ? 0 : getEndSection().hashCode());
        result = prime * result + ((getPlace() == null) ? 0 : getPlace().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", kcmc=").append(kcmc);
        sb.append(", kcbh=").append(kcbh);
        sb.append(", kxh=").append(kxh);
        sb.append(", week=").append(week);
        sb.append(", day=").append(day);
        sb.append(", startSection=").append(startSection);
        sb.append(", endSection=").append(endSection);
        sb.append(", place=").append(place);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}