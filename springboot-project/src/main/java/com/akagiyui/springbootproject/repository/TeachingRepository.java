package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 教学表操作接口
 * @author AkagiYui
 */
@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {

}
