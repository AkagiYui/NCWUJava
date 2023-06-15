package com.akagiyui.springbootproject.entity.request;

import com.akagiyui.springbootproject.entity.Course;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

/**
 * 筛选查询课程 请求
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class CourseFilterRequest {

    /**
     * 课程名称
     */
    String name;

    /**
     * 转换为 Course 对象
     * @return Course 对象
     */
    public Course toCourse() {
        return new Course()
                .setName(StringUtils.hasText(name) ? name : null);
    }
}
