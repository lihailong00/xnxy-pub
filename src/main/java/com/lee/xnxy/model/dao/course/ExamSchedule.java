package com.lee.xnxy.model.dao.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName xnxy_exam_schedule
 */
@TableName(value ="xnxy_exam_schedule")
@Data
public class ExamSchedule implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生学号
     */
    @TableField(value = "jwc_username")
    private String jwcUsername;

    /**
     * 考试场次
     */
    @TableField(value = "kscc")
    private String kscc;

    /**
     * 课程编号
     */
    @TableField(value = "kcbh")
    private String kcbh;

    /**
     * 课程名称
     */
    @TableField(value = "kcmc")
    private String kcmc;

    /**
     * 考试时间
     */
    @TableField(value = "kssj")
    private String kssj;

    /**
     * 考场
     */
    @TableField(value = "kc")
    private String kc;

    /**
     * 座位号
     */
    @TableField(value = "zwh")
    private String zwh;

    /**
     * 考试学期
     */
    @TableField(value = "term")
    private String term;

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
        ExamSchedule other = (ExamSchedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getJwcUsername() == null ? other.getJwcUsername() == null : this.getJwcUsername().equals(other.getJwcUsername()))
            && (this.getKscc() == null ? other.getKscc() == null : this.getKscc().equals(other.getKscc()))
            && (this.getKcbh() == null ? other.getKcbh() == null : this.getKcbh().equals(other.getKcbh()))
            && (this.getKcmc() == null ? other.getKcmc() == null : this.getKcmc().equals(other.getKcmc()))
            && (this.getKssj() == null ? other.getKssj() == null : this.getKssj().equals(other.getKssj()))
            && (this.getKc() == null ? other.getKc() == null : this.getKc().equals(other.getKc()))
            && (this.getZwh() == null ? other.getZwh() == null : this.getZwh().equals(other.getZwh()))
            && (this.getTerm() == null ? other.getTerm() == null : this.getTerm().equals(other.getTerm()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getJwcUsername() == null) ? 0 : getJwcUsername().hashCode());
        result = prime * result + ((getKscc() == null) ? 0 : getKscc().hashCode());
        result = prime * result + ((getKcbh() == null) ? 0 : getKcbh().hashCode());
        result = prime * result + ((getKcmc() == null) ? 0 : getKcmc().hashCode());
        result = prime * result + ((getKssj() == null) ? 0 : getKssj().hashCode());
        result = prime * result + ((getKc() == null) ? 0 : getKc().hashCode());
        result = prime * result + ((getZwh() == null) ? 0 : getZwh().hashCode());
        result = prime * result + ((getTerm() == null) ? 0 : getTerm().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", jwcUsername=").append(jwcUsername);
        sb.append(", kscc=").append(kscc);
        sb.append(", kcbh=").append(kcbh);
        sb.append(", kcmc=").append(kcmc);
        sb.append(", kssj=").append(kssj);
        sb.append(", kc=").append(kc);
        sb.append(", zwh=").append(zwh);
        sb.append(", term=").append(term);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}