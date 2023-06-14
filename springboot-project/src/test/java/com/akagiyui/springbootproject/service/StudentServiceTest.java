package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;

/**
 * 学生 服务 测试
 * @author AkagiYui
 */
@SpringBootTest
class StudentServiceTest {

    @Resource
    StudentService studentService;

    @Test
    @DisplayName("分页查询")
    void find() {
        Page<Student> students = studentService.find(4, 4);
        students.forEach(System.out::println);
    }
}
