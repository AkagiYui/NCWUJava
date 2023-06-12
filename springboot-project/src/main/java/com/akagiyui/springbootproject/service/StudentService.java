package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 学生 服务
 * @author AkagiYui
 */
@Service
public class StudentService {

    @Resource
    StudentRepository studentRepository;

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页大小
     * @return 分页结果
     */
    public Page<Student> find(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

}
