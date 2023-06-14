package com.akagiyui.springbootproject.entity.request;


import com.akagiyui.springbootproject.entity.Student;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 添加学生 请求
 * @author AkagiYui
 */
@Data
public class AddStudentRequest {
    /**
     * 学号
     */
    @NotBlank(message = "Number cannot be empty")
    @NotNull(message = "Number cannot be empty")
    @Size(min = 3, max = 20, message = "Number length must be between 3 and 20")
    private String number;

    /**
     * 姓名
     */
    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be empty")
    private String name;

    /**
     * 班级
     */
    @Size(min = 3, max = 20, message = "ClassName length must be less than 20")
    private String className;

    /**
     * 转换为 Student 对象
     * @return Student 对象
     */
    public Student toStudent() {
        return new Student()
                .setNumber(number)
                .setName(name)
                .setClassName(className);
    }
}
