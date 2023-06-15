package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 课程表操作接口
 * @author AkagiYui
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * 根据课程名判断是否存在
     * @param name 课程名
     * @return 是否存在
     */
    Boolean existsByName(String name);

    /**
     * 根据课程名查找
     * @param name 课程名
     * @return 课程
     */
    Course findByName(String name);
}
