package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 教师表操作接口
 * @author AkagiYui
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
