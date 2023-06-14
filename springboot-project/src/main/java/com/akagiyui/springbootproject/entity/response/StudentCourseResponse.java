package com.akagiyui.springbootproject.entity.response;

import com.akagiyui.springbootproject.entity.Course;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 学生课程信息 响应
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class StudentCourseResponse {
    /**
     * 学生id
     */
    Long id;

    /**
     * 课程
     */
    List<Course> courses;
}
