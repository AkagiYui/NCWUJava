package com.akagiyui.springbootproject.controller;

import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.Teacher;
import com.akagiyui.springbootproject.entity.request.AddTeacherRequest;
import com.akagiyui.springbootproject.entity.request.TeacherFilterRequest;
import com.akagiyui.springbootproject.entity.request.UpdateCourseRequest;
import com.akagiyui.springbootproject.entity.response.CourseResponse;
import com.akagiyui.springbootproject.entity.response.PageResponse;
import com.akagiyui.springbootproject.entity.response.TeacherPageResponse;
import com.akagiyui.springbootproject.entity.response.TeacherResponse;
import com.akagiyui.springbootproject.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 教师 API
 * @author AkagiYui
 */
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 每页大小
     * @return 分页响应
     */
    @GetMapping("")
    public PageResponse<TeacherResponse> getTeacherPage(@RequestParam Integer page, @RequestParam Integer size, @ModelAttribute TeacherFilterRequest filter) {
        Page<Teacher> teachers = teacherService.find(page, size, filter);
        List<Teacher> teacherList = teachers.getContent();
        List<TeacherResponse> teacherResponseList = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            TeacherResponse teacherResponse = new TeacherResponse();
            BeanUtils.copyProperties(teacher, teacherResponse);
            teacherResponseList.add(teacherResponse);
        }

        return new TeacherPageResponse()
                .setPage(page)
                .setSize(size)
                .setPageCount(teachers.getTotalPages())
                .setTotal(teachers.getTotalElements())
                .setList(teacherResponseList);
    }

    /**
     * 根据 ID 删除教师
     *
     * @param id 教师 ID
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return teacherService.delete(id);
    }

    /**
     * 根据 ID 查询任教课程
     *
     * @param id 教师 ID
     * @return 课程列表
     */
    @GetMapping("/{id}/courses")
    public List<CourseResponse> showCourses(@PathVariable Long id) {
        List<Course> course = teacherService.getCourseByTeacherId(id);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course c : course) {
            CourseResponse courseResponse = new CourseResponse();
            BeanUtils.copyProperties(c, courseResponse);
            courseResponseList.add(courseResponse);
        }
        return courseResponseList;
    }

    /**
     * 更新教师任教课程
     *
     * @param id        教师 ID
     * @param courseIds 课程 ID 列表
     */
    @PutMapping("/{id}/courses")
    public Boolean updateCourses(@PathVariable Long id, @RequestBody List<Long> courseIds) {
        return teacherService.updateCourseByTeacherId(id, courseIds.stream()
                .map(courseId -> new UpdateCourseRequest().setId(courseId))
                .collect(Collectors.toList()));
    }

    /**
     * 创建教师
     */
    @PostMapping("")
    public Boolean create(@RequestBody @Validated AddTeacherRequest teacher) {
        return teacherService.create(teacher);
    }

    /**
     * 更新教师信息
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable Long id, @RequestBody @Validated AddTeacherRequest student) {
        return teacherService.update(id, student);
    }
}
