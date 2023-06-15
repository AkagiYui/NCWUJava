package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Enrollment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author AkagiYui
 */
@SpringBootTest
class EnrollmentRepositoryTest {
    @Resource
    EnrollmentRepository enrollmentRepository;

    @Resource
    StudentRepository studentRepository;

    @Resource
    CourseRepository courseRepository;

    @Test
    @Transactional
    void showAllEnrollments() {
        enrollmentRepository.findAll().forEach(System.out::println);
    }

    @Test
    void addEnrollment() {
        enrollmentRepository.save(
                new Enrollment()
                        .setStudent(studentRepository.findById(5L).get())
                        .setCourse(courseRepository.findById(3L).get())

        );
    }
}
