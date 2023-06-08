package com.akagiyui.springbootproject.controller;

import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author AkagiYui
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentService studentService;

    @GetMapping("/page/{page}/size/{size}")
    public List<Student> showAll(@PathVariable Integer page, @PathVariable Integer size) {
        return studentService.find(page, size).getContent();
    }
}
