package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.component.ResponseEnum;
import com.akagiyui.springbootproject.entity.*;
import com.akagiyui.springbootproject.exception.CustomException;
import com.akagiyui.springbootproject.repository.CourseRepository;
import com.akagiyui.springbootproject.repository.TeacherRepository;
import com.akagiyui.springbootproject.repository.TeachingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 教学 服务
 * @author AkagiYui
 */
@Service
public class TeachingService {

    @Resource
    TeachingRepository teachingRepository;

    @Resource
    TeacherRepository teacherRepository;

    @Resource
    CourseRepository courseRepository;

    /**
     * 根据课程ID查找任教教师
     * @param courseId 课程ID
     * @return 教师列表
     */
    public List<Teacher> findTeachesByCourseId(Long courseId) {
        List<Teaching> teachings = teachingRepository.findAllByCourseId(courseId);
        return teachings.stream().map(Teaching::getTeacher).collect(Collectors.toList());
    }

    /**
     * 存储任课信息
     */
    public Boolean save(Long teacherId, Long courseId) {
        if (teachingRepository.existsByTeacherIdAndCourseId(teacherId, courseId)) {
            return true;
        }
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        teachingRepository.save(new Teaching(teacher, course));
        return true;
    }
}
