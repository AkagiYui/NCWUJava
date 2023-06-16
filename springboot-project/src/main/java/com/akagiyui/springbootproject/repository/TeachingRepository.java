package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教学表操作接口
 * @author AkagiYui
 */
@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {
    /**
     * 根据课程查找任教教师
     * @param courseId 课程ID
     * @return 教师列表
     */
    List<Teaching> findAllByCourseId(Long courseId);

    /** 根据教师ID和课程ID判断是否存在 */
    Boolean existsByTeacherIdAndCourseId(Long teacherId, Long courseId);
}
