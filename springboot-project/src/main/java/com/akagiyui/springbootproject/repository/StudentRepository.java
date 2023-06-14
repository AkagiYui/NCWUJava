package com.akagiyui.springbootproject.repository;


import com.akagiyui.springbootproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 学生表操作接口
 * @author AkagiYui
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * 根据学号判断是否存在
     * @param number 学号
     * @return 是否存在
     */
    Boolean existsByNumber(String number);

    /**
     * 根据学号查找
     * @param number 学号
     * @return 学生
     */
    Student findByNumber(String number);
}
