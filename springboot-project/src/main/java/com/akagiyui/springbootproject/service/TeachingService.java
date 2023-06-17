package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Teacher;
import com.akagiyui.springbootproject.entity.Teaching;
import com.akagiyui.springbootproject.repository.TeachingRepository;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    TeacherService teacherService;

    @Resource
    CourseService courseService;

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
        Teacher teacher = teacherService.find(teacherId);
        Course course = courseService.find(courseId);
        teachingRepository.save(new Teaching(teacher, course));
        return true;
    }

    public void delete(Long teacherId, Long courseId) {
        Teaching teaching = teachingRepository.findByTeacherIdAndCourseId(teacherId, courseId);
        teachingRepository.delete(teaching);
    }

    public Integer countByTeacherId(Long teacherId) {
        return teachingRepository.countByTeacherId(teacherId);
    }

    public Integer delete(Course course) {
        return teachingRepository.deleteAllByCourseId(course.getId());
    }
}
