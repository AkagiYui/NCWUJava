package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.component.ResponseEnum;
import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Teacher;
import com.akagiyui.springbootproject.entity.request.AddTeacherRequest;
import com.akagiyui.springbootproject.entity.request.TeacherFilterRequest;
import com.akagiyui.springbootproject.entity.request.UpdateCourseRequest;
import com.akagiyui.springbootproject.exception.CustomException;
import com.akagiyui.springbootproject.repository.TeacherRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 教师 服务
 * @author AkagiYui
 */
@Service
public class TeacherService {

    @Resource
    TeacherRepository teacherRepository;

    @Resource
    TeachingService teachingService;

    /**
     * 分页查询
     *
     * @param page   页码
     * @param size   每页大小
     * @param filter 过滤条件
     * @return 分页结果
     */
    public Page<Teacher> find(Integer page, Integer size, TeacherFilterRequest filter) {
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("number", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("phone", ExampleMatcher.GenericPropertyMatcher::contains);

        return teacherRepository.findAll(Example.of(filter.toTeacher(), matcher), pageable);
    }

    /**
     * 删除教师
     *
     * @param id 教师 ID
     * @return 是否成功
     */
    public Boolean delete(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new CustomException(ResponseEnum.NOT_FOUND);
        }
        teacherRepository.deleteById(id);
        return true;
    }

    /**
     * 根据 ID 查询教师课程
     *
     * @param id 教师 ID
     * @return 课程列表
     */
    public List<Course> getCourseByTeacherId(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        return teacher.getCourses();
    }

    /**
     * 根据 ID 更新教师课程
     *
     * @param id      教师 ID
     * @param courses 课程列表
     * @return 是否成功
     */
    @Transactional
    public Boolean updateCourseByTeacherId(Long id, List<UpdateCourseRequest> courses) {
        for (UpdateCourseRequest course : courses) {
            teachingService.save(id, course.getId());
        }
        return true;
    }

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     * @return 是否成功
     */
    public Boolean create(AddTeacherRequest teacher) {
        if (teacherRepository.existsByNumber(teacher.getNumber())) {
            throw new CustomException(ResponseEnum.EXIST);
        }
        teacherRepository.save(teacher.toTeacher());
        return true;
    }

    /**
     * 更新教师信息
     *
     * @param id      教师 ID
     * @param teacher 教师信息
     * @return 是否成功
     */
    public Boolean update(Long id, AddTeacherRequest teacher) {
        Teacher oldTeacher = teacherRepository.findById(id).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        if (StringUtils.hasText(teacher.getNumber())) {
            // 如果工号已存在，且不是当前教师的工号，则报错
            if (teacherRepository.existsByNumber(teacher.getNumber())) {
                Teacher teacher1 = teacherRepository.findByNumber(teacher.getNumber());
                if (!Objects.equals(teacher1.getId(), id)) {
                    throw new CustomException(ResponseEnum.EXIST);
                }
            }
            oldTeacher.setNumber(teacher.getNumber());
        }
        if (StringUtils.hasText(teacher.getName())) {
            oldTeacher.setName(teacher.getName());
        }
        if (StringUtils.hasText(teacher.getPhone())) {
            oldTeacher.setPhone(teacher.getPhone());
        }
        teacherRepository.save(oldTeacher);
        return true;
    }
}
