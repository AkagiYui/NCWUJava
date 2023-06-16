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
    /**
     * 根据工号判断是否存在
     * @param number 工号
     * @return 是否存在
     */
    Boolean existsByNumber(String number);

    /**
     * 根据工号查找
     * @param number 工号
     * @return 教师
     */
    Teacher findByNumber(String number);
}
