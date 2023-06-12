package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 教师 实体
 * @author AkagiYui
 */
@Data
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
    String teacherNumber;
}
