package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "enrollment")
public class Enrollment extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
