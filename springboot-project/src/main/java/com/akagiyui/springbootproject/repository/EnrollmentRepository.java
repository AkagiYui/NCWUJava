package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 选课表操作接口
 * @author AkagiYui
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    /** 根据学生ID和课程ID判断是否存在 */
    Boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    /** 根据学生ID和课程ID查找选课信息 */
    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}
