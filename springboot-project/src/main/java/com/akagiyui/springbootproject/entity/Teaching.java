package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 教学 实体
 * @author AkagiYui
 */
@Data
@Entity
@NoArgsConstructor
@Table
@Accessors(chain = true)
public class Teaching extends BaseEntity{
    /**
     * 教师
     */
    @ManyToOne
    @JoinColumn(name="teacher_id")
    Teacher teacher;

    /**
     * 课程
     */
    @ManyToOne
    @JoinColumn(name="course_id")
    Course course;

    public Teaching(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }
}
