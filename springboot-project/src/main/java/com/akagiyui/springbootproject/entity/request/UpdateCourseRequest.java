package com.akagiyui.springbootproject.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 更新课程 请求
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class UpdateCourseRequest {
    Long id;
}
