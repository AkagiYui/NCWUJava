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

}
