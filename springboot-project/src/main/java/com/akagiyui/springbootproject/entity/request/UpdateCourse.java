package com.akagiyui.springbootproject.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 更新课程 请求
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class UpdateCourse {
    /**
     * 课程 ID
     */
    @NotNull(message = "Course ID cannot be empty")
    Long id;
}
