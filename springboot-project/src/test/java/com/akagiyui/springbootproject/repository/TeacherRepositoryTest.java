package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author AkagiYui
 */
@SpringBootTest
class TeacherRepositoryTest {

    @Resource
    TeacherRepository teacherRepository;

    @Test
    void addTeacher() {
        List<Teacher> teachers = Arrays.asList(
                new Teacher("马永鹏", "100001", "13700000000")
        );
        teacherRepository.saveAll(teachers);
    }
}
