package com.akagiyui.springbootproject.repository;

import com.akagiyui.springbootproject.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author AkagiYui
 */

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
