package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * @author AkagiYui
 */
@Data
@ToString(exclude = "courses")
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table
public class Student extends BaseEntity{
    @Column(nullable = false, unique = true)
    String number;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String className;

    public Student(String number, String name, String className) {
        this.number = number;
        this.name = name;
        this.className = className;
    }

    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    List<Course> courses;
}
