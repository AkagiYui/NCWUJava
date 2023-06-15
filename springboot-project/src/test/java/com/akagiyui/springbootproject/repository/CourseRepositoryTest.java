package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author AkagiYui
 */
@SpringBootTest
class CourseRepositoryTest {

    @Resource
    CourseRepository courseRepository;

    @Test
    @Transactional
    void showAllCourses() {
        courseRepository.findAll().forEach(course -> {
            System.out.println(course);
            System.out.println(course.getEnrollments());
        });
    }

    @Test
    void addCourse() {
        courseRepository.saveAll(Arrays.asList(
                new Course("计算机网络"),
                new Course("计算机组织结构"),
                new Course("操作系统"),
                new Course("Python语言程序设计"),
                new Course("软件构造")
        ));
    }
}
