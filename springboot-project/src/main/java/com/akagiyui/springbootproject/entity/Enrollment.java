package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 选课 实体
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "enrollment")
public class Enrollment extends BaseEntity{
    /**
     * 学生
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * 课程
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
