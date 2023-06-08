package com.akagiyui.springbootproject.repository;


import com.akagiyui.springbootproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户表操作接口
 * @author AkagiYui
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
