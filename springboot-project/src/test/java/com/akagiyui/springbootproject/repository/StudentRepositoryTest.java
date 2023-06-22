package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author AkagiYui
 */
@SpringBootTest
class StudentRepositoryTest {
    @Resource
    StudentRepository studentRepository;

    @Test
    @Transactional
    void showAll() {
        studentRepository.findAll().forEach(System.out::println);
    }

    @Test
    void addStudent() {
        List<Student> studentList = Arrays.asList(
                new Student("202018301", "大乱战", "2020183")
        );
        studentRepository.saveAll(studentList);
    }
}
