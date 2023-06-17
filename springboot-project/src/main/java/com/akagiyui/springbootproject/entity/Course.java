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
 * 课程 实体
 * @author AkagiYui
 */
@Data
@ToString(exclude = {"enrollments", "teachings"})
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table
public class Course extends BaseEntity{
    /**
     * 课程名称
     */
    @Column(nullable = false, unique = true)
    String name;

    /**
     * 选课记录
     */
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    /**
     * 任教记录
     */
    @OneToMany(mappedBy = "course")
    private List<Teaching> teachings;

    public Course(String name) {
        this.name = name;
    }

}
