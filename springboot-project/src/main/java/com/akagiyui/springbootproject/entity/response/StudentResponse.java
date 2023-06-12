package com.akagiyui.springbootproject.entity.response;

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
}
