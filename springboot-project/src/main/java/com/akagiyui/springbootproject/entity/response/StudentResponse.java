package com.akagiyui.springbootproject.entity.response;

import com.akagiyui.springbootproject.entity.Student;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 学生信息 响应
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class StudentResponse {
    /**
     * 学生id
     */
    Long id;

    /**
     * 学号
     */
    String number;

    /**
     * 姓名
     */
    String name;

    /**
     * 班级
     */
    String className;

    /** 转换为 StudentResponse 对象 */
    public static StudentResponse fromStudent(Student student) {
        return new StudentResponse()
                .setId(student.getId())
                .setName(student.getName())
                .setNumber(student.getNumber())
                .setClassName(student.getClassName());
    }
}
