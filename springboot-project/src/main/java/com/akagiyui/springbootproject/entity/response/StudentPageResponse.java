package com.akagiyui.springbootproject.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 学生分页 响应
 *
 * @author AkagiYui
 */
@Data
@Accessors(chain = true)
public class StudentPageResponse {
    /**
     * 当前页
     */
    Integer page;

    /**
     * 每页大小
     */
    Integer size;

    /**
     * 页数
     */
    Integer pageCount;

    /**
     * 总数
     */
    Long total;

    /**
     * 内容
     */
    List<StudentResponse> list;
}
