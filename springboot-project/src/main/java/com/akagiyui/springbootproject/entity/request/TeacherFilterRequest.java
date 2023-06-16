package com.akagiyui.springbootproject.entity.request;

import com.akagiyui.springbootproject.entity.Teacher;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

/**
 * 筛选查询教师 请求
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class TeacherFilterRequest {
    /**
     * 学号
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
     * 转换为 Teacher 对象
     * @return Teacher 对象
     */
    public Teacher toTeacher() {
        return new Teacher()
                .setNumber(StringUtils.hasText(number) ? number : null)
                .setName(StringUtils.hasText(name) ? name : null)
                .setPhone(StringUtils.hasText(phone) ? phone : null);
    }
}
