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
    Boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
