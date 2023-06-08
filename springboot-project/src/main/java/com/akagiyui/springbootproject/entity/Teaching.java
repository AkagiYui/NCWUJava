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
@Entity
@Table
@Accessors(chain = true)
public class Teaching extends BaseEntity{
    @ManyToOne
    @JoinColumn(name="teacher_id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name="course_id")
    Course course;
}
