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
 * @TableName xnxy_course_and_teacher
 */
@TableName(value ="xnxy_course_and_teacher")
@Data
public class CourseAndTeacher implements Serializable {
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
    @TableField(value = "teacher_name")
    private String teacherName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseAndTeacher that = (CourseAndTeacher) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(kcbh, that.kcbh)) return false;
        if (!Objects.equals(kxh, that.kxh)) return false;
        return Objects.equals(teacherName, that.teacherName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (kcbh != null ? kcbh.hashCode() : 0);
        result = 31 * result + (kxh != null ? kxh.hashCode() : 0);
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseAndTeacher{" +
                "id=" + id +
                ", kcbh='" + kcbh + '\'' +
                ", kxh=" + kxh +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}