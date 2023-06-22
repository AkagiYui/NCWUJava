package com.akagiyui.springbootproject.entity.filter;

import com.akagiyui.springbootproject.entity.Student;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

/**
 * 筛选查询学生 请求
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class StudentFilter {
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

    /**
     * 转换为 Student 对象
     * @return Student 对象
     */
    public Student toStudent() {
        return new Student()
                .setNumber(StringUtils.hasText(number) ? number : null)
                .setName(StringUtils.hasText(name) ? name : null)
                .setClassName(StringUtils.hasText(className) ? className : null);
    }
}
