package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.component.ResponseEnum;
import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Enrollment;
import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.exception.CustomException;
import com.akagiyui.springbootproject.repository.CourseRepository;
import com.akagiyui.springbootproject.repository.EnrollmentRepository;
import com.akagiyui.springbootproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 选课 服务
 * @author AkagiYui
 */
@Service
public class EnrollmentService {
    @Resource
    EnrollmentRepository enrollmentRepository;

    @Resource
    StudentRepository studentRepository;

    @Resource
    CourseRepository courseRepository;

    /**
     * 存储选课信息
     */
    public Boolean save(Long studentId, Long courseId) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            return true;
        }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        enrollmentRepository.save(new Enrollment(student, course));
        return true;
    }
}
