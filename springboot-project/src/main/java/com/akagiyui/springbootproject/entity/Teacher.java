package com.akagiyui.springbootproject.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author AkagiYui
 */
@Data
@Entity
@Table
@Accessors(chain = true)
public class Teacher extends BaseEntity{
    String name;
    String teacherNumber;
}
