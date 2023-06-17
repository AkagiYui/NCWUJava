package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Enrollment;
import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.repository.EnrollmentRepository;
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
    StudentService studentService;

    @Resource
    CourseService courseService;

    /**
     * 存储选课信息
     */
    public Boolean save(Long studentId, Long courseId) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            return true;
        }
        Student student = studentService.find(studentId);
        Course course = courseService.find(courseId);
        enrollmentRepository.save(new Enrollment(student, course));
        return true;
    }

    public void delete(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        enrollmentRepository.delete(enrollment);
    }
}
