package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 学生 实体
 * @author AkagiYui
 */
@Data
@ToString(exclude = "courses")
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table
public class Student extends BaseEntity{
    /**
     * 学号
     */
    @Column(nullable = false, unique = true)
    String number;

    /**
     * 姓名
     */
    @Column(nullable = false)
    String name;

    /**
     * 班级
     */
    @Column(nullable = false)
    String className;

    /**
     * 所选课程
     */
    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    List<Course> courses;

    public Student(String number, String name, String className) {
        this.number = number;
        this.name = name;
        this.className = className;
    }
}
