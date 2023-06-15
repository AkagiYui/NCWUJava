package com.akagiyui.springbootproject.entity.request;


import com.akagiyui.springbootproject.entity.Course;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加课程 请求
 * @author AkagiYui
 */
@Data
public class AddCourseRequest {

    /**
     * 课程名称
     */
    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be empty")
    private String name;


    /**
     * 转换为 Course 对象
     * @return Course 对象
     */
    public Course toCourse() {
        return new Course()
                .setName(name);
    }
}
