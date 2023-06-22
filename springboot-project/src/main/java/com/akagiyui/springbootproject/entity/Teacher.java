package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 教师 实体
 * @author AkagiYui
 */
@Data
@ToString(exclude = "courses")
@NoArgsConstructor
@Entity
@Table
@Accessors(chain = true)
public class Teacher extends BaseEntity{
    /**
     * 教师姓名
     */
    String name;

    /**
     * 教师工号
     */
    @Column(unique = true)
    String number;

    /**
     * 联系电话
     */
    String phone;

    /**
     * 所教课程
     */
    @ManyToMany
    @JoinTable(
            name = "teaching",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    List<Course> courses;

    public Teacher(String name, String number, String phone) {
        this.name = name;
        this.number = number;
        this.phone = phone;
    }
}
