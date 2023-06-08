package com.akagiyui.springbootproject.service;

import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author AkagiYui
 */
@Service
public class StudentService {

    // 只有一个实现类还要定义接口是为什么

    @Resource
    StudentRepository studentRepository;

    public Page<Student> find(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

}
