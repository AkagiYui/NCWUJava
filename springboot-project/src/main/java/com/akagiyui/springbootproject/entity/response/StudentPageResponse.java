package com.akagiyui.springbootproject.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 学生分页 响应
 *
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class StudentPageResponse extends PageResponse<StudentResponse>{
}
