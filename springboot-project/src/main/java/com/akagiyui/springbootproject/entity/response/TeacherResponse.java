package com.akagiyui.springbootproject.entity.response;

import com.akagiyui.springbootproject.entity.Teacher;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息 响应
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class TeacherResponse {
    /**
     * 教师id
     */
    Long id;

    /**
     * 教师工号
     */
    String number;

    /**
     * 姓名
     */
    String name;

    /**
     * 联系电话
     */
    String phone;

    /**
     * 任教课程数量
     */
    Integer courseCount;

    /** 转换为 TeacherResponse 对象 */
    public static TeacherResponse fromTeacher(Teacher teacher) {
        return new TeacherResponse()
                .setId(teacher.getId())
                .setName(teacher.getName())
                .setNumber(teacher.getNumber())
                .setPhone(teacher.getPhone());
    }
}
