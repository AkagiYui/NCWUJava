package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author AkagiYui
 */
@Data
@ToString(exclude = "enrollments")
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table
public class Course extends BaseEntity{
    @Column(nullable = false, unique = true)
    String name;

    public Course(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}
