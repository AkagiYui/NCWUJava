package com.akagiyui.springbootproject.controller;

import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Student;
import com.akagiyui.springbootproject.entity.request.AddStudentRequest;
import com.akagiyui.springbootproject.entity.request.StudentFilterRequest;
import com.akagiyui.springbootproject.entity.request.UpdateStudentCourseRequest;
import com.akagiyui.springbootproject.entity.response.CourseResponse;
import com.akagiyui.springbootproject.entity.response.PageResponse;
import com.akagiyui.springbootproject.entity.response.StudentPageResponse;
import com.akagiyui.springbootproject.entity.response.StudentResponse;
import com.akagiyui.springbootproject.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生 API
 *
 * @author AkagiYui
 */
@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Resource
    StudentService studentService;

    /**
     * 分页查询学生
     *
     * @param page 页码
     * @param size 每页大小
     * @return 学生分页响应
     */
    @GetMapping("")
    public PageResponse<StudentResponse> getStudentPage(@RequestParam Integer page, @RequestParam Integer size, @ModelAttribute StudentFilterRequest filter) {
        Page<Student> students = studentService.find(page, size, filter);
        List<Student> studentList = students.getContent();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (Student student : studentList) {
            StudentResponse studentResponse = new StudentResponse();
            BeanUtils.copyProperties(student, studentResponse);
            studentResponseList.add(studentResponse);
        }

        return new StudentPageResponse()
                .setPage(page)
                .setSize(size)
                .setPageCount(students.getTotalPages())
                .setTotal(students.getTotalElements())
                .setList(studentResponseList);
    }

    /**
     * 根据 ID 删除学生
     *
     * @param id 学生 ID
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return studentService.delete(id);
    }

    /**
     * 根据 ID 查询学生所选课程
     *
     * @param id 学生 ID
     * @return 课程列表
     */
    @GetMapping("/{id}/courses")
    public List<CourseResponse> showCourses(@PathVariable Long id) {
        List<Course> course = studentService.getCourseByStudentId(id);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course c : course) {
            CourseResponse courseResponse = new CourseResponse();
            BeanUtils.copyProperties(c, courseResponse);
            courseResponseList.add(courseResponse);
        }
        return courseResponseList;
    }

    /**
     * 更新学生所选课程
     *
     * @param id        学生 ID
     * @param courseIds 课程 ID 列表
     */
    @PutMapping("/{id}/courses")
    public Boolean updateCourses(@PathVariable Long id, @RequestBody List<Long> courseIds) {
        return studentService.updateCourseByStudentId(id, courseIds.stream()
                .map(courseId -> new UpdateStudentCourseRequest().setId(courseId))
                .collect(Collectors.toList()));
    }

    /**
     * 创建学生
     */
    @PostMapping("")
    public Boolean create(@RequestBody AddStudentRequest student) {
        return studentService.create(student);
    }

    /**
     * 更新学生信息
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable Long id, @RequestBody AddStudentRequest student) {
        return studentService.update(id, student);
    }
}
