package com.akagiyui.springbootproject.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师分页 响应
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class TeacherPageResponse extends PageResponse<TeacherResponse>{
}
