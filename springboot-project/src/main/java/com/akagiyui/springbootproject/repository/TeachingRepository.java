package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author AkagiYui
 */

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {

}
