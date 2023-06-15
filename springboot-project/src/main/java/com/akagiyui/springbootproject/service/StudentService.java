package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.component.ResponseEnum;
import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.entity.request.AddStudentRequest;
import com.akagiyui.springbootproject.entity.request.StudentFilterRequest;
import com.akagiyui.springbootproject.entity.request.UpdateStudentCourseRequest;
import com.akagiyui.springbootproject.exception.CustomException;
import com.akagiyui.springbootproject.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 学生 服务
 *
 * @author AkagiYui
 */
@Service
public class StudentService {

    @Resource
    StudentRepository studentRepository;

    @Resource
    EnrollmentService enrollmentService;

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 每页大小
     * @return 分页结果
     */
    public Page<Student> find(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    /**
     * 分页查询
     *
     * @param page   页码
     * @param size   每页大小
     * @param filter 过滤条件
     * @return 分页结果
     */
    public Page<Student> find(Integer page, Integer size, StudentFilterRequest filter) {
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("className", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("number", ExampleMatcher.GenericPropertyMatcher::contains);

        return studentRepository.findAll(Example.of(filter.toStudent(), matcher), pageable);
    }

    /**
     * 删除学生
     *
     * @param id 学生 ID
     * @return 是否成功
     */
    public Boolean delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new CustomException(ResponseEnum.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        return true;
    }

    /**
     * 根据 ID 查询学生课程
     *
     * @param id 学生 ID
     * @return 课程列表
     */
    public List<Course> getCourseByStudentId(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        return student.getCourses();
    }

    /**
     * 根据 ID 更新学生课程
     *
     * @param id      学生 ID
     * @param courses 课程列表
     * @return 是否成功
     */
    @Transactional
    public Boolean updateCourseByStudentId(Long id, List<UpdateStudentCourseRequest> courses) {
        for (UpdateStudentCourseRequest course : courses) {
            enrollmentService.save(id, course.getId());
        }
        return true;
    }

    /**
     * 添加学生
     *
     * @param student 学生信息
     * @return 是否成功
     */
    public Boolean create(AddStudentRequest student) {
        if (studentRepository.existsByNumber(student.getNumber())) {
            throw new CustomException(ResponseEnum.EXIST);
        }
        studentRepository.save(student.toStudent());
        return true;
    }

    /**
     * 更新学生信息
     *
     * @param id      学生 ID
     * @param student 学生信息
     * @return 是否成功
     */
    public Boolean update(Long id, AddStudentRequest student) {
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        if (StringUtils.hasText(student.getNumber())) {
            // 如果学号已存在，且不是当前学生的学号，则报错
            if (studentRepository.existsByNumber(student.getNumber())) {
                Student student1 = studentRepository.findByNumber(student.getNumber());
                if (!Objects.equals(student1.getId(), id)) {
                    throw new CustomException(ResponseEnum.EXIST);
                }
            }
            oldStudent.setNumber(student.getNumber());
        }
        if (StringUtils.hasText(student.getName())) {
            oldStudent.setName(student.getName());
        }
        if (StringUtils.hasText(student.getClassName())) {
            oldStudent.setClassName(student.getClassName());
        }
        studentRepository.save(oldStudent);
        return true;
    }
}
