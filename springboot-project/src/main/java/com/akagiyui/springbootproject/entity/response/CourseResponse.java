package com.akagiyui.springbootproject.entity.response;

import com.akagiyui.springbootproject.entity.Course;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息 响应
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class CourseResponse {
    /**
     * 课程id
     */
    Long id;
    /**
     * 课程名称
     */
    String name;

    /**
     * 任教教师数量
     */
    Integer teachingCount;

    /**
     * 选课人数
     */
    Integer enrolledCount;

    /**
     * 从 Course 对象转换为 CourseResponse 对象
     * @param course Course 对象
     * @return CourseResponse 对象
     */
    public static CourseResponse fromCourse(Course course) {
        return new CourseResponse()
                .setId(course.getId())
                .setName(course.getName());
    }
}
