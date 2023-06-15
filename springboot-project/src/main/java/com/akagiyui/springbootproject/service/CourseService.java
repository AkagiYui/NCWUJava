package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.component.ResponseEnum;
import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.request.AddCourseRequest;
import com.akagiyui.springbootproject.entity.request.CourseFilterRequest;
import com.akagiyui.springbootproject.exception.CustomException;
import com.akagiyui.springbootproject.repository.CourseRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 课程 服务
 * @author AkagiYui
 */
@Service
public class CourseService {
    @Resource
    CourseRepository courseRepository;

    /**
     * 分页查询
     *
     * @param page   页码
     * @param size   每页大小
     * @param filter 过滤条件
     * @return 分页结果
     */
    public Page<Course> find(Integer page, Integer size, CourseFilterRequest filter) {
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains);

        return courseRepository.findAll(Example.of(filter.toCourse(), matcher), pageable);
    }

    /**
     * 查询所有
     */
    public List<Course> find() {
        return courseRepository.findAll();
    }

    /**
     * 删除课程
     */
    public Boolean delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CustomException(ResponseEnum.NOT_FOUND);
        }
        courseRepository.deleteById(id);
        return true;
    }

    /**
     * 添加课程
     */
    public Boolean create(AddCourseRequest course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new CustomException(ResponseEnum.EXIST);
        }
        courseRepository.save(course.toCourse());
        return true;
    }

    /**
     * 更新课程
     */
    public Boolean update(Long id, AddCourseRequest course) {
        Course oldCourse = courseRepository.findById(id).orElseThrow(() -> new CustomException(ResponseEnum.NOT_FOUND));
        if (courseRepository.existsByName(course.getName())) {
            Course course1 = courseRepository.findByName(course.getName());
            if (!Objects.equals(course1.getId(), id)) {
                throw new CustomException(ResponseEnum.EXIST);
            }
        }
        if (StringUtils.hasText(course.getName())) {
            oldCourse.setName(course.getName());
        }
        courseRepository.save(oldCourse);
        return true;
    }
}
