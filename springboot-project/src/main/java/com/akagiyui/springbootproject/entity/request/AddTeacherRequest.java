package com.akagiyui.springbootproject.entity.request;


import com.akagiyui.springbootproject.entity.Teacher;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 添加教师 请求
 * @author AkagiYui
 */
@Data
public class AddTeacherRequest {
    /**
     * 工号
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
     * 联系电话
     */
    @Size(min = 8, max = 20, message = "Phone number length must be less than 20")
    private String phone;

    /**
     * 转换为 Teacher 对象
     * @return Teacher 对象
     */
    public Teacher toTeacher() {
        return new Teacher()
                .setNumber(number)
                .setName(name)
                .setPhone(phone);
    }
}
