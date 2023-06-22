package com.akagiyui.springbootproject.controller;

import com.akagiyui.springbootproject.entity.Course;
import com.akagiyui.springbootproject.entity.request.AddCourse;
import com.akagiyui.springbootproject.entity.filter.CourseFilter;
import com.akagiyui.springbootproject.entity.response.CoursePageResponse;
import com.akagiyui.springbootproject.entity.response.CourseResponse;
import com.akagiyui.springbootproject.entity.response.PageResponse;
import com.akagiyui.springbootproject.entity.response.TeacherResponse;
import com.akagiyui.springbootproject.service.CourseService;
import com.akagiyui.springbootproject.service.TeachingService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程 API
 * @author AkagiYui
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Resource
    CourseService courseService;

    @Resource
    TeachingService teachingService;

    /**
     * 分页查询课程
     * @param page 页码
     * @param size 每页大小
     * @return 学生分页响应
     */
    @GetMapping("")
    public PageResponse<CourseResponse> getCoursePage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @ModelAttribute CourseFilter filter
    ) {
        Page<Course> courses = courseService.find(page, size, filter);
        List<Course> courseList = courses.getContent();
        List<CourseResponse> courseResponseList = courseList.stream()
                .map((course -> {
                    CourseResponse courseResponse = CourseResponse.fromCourse(course);
                    courseResponse.setTeachingCount(course.getTeachings().size());
                    courseResponse.setEnrolledCount(course.getEnrollments().size());
                    return courseResponse;
                }))
                .collect(Collectors.toList());

        return new CoursePageResponse()
                .setPage(page)
                .setSize(size)
                .setPageCount(courses.getTotalPages())
                .setTotal(courses.getTotalElements())
                .setList(courseResponseList);
    }

    /**
     * 查询所有课程
     */
    @GetMapping("/all")
    public List<CourseResponse> getCourse() {
        return courseService.find().stream()
                .map(CourseResponse::fromCourse)
                .collect(Collectors.toList());
    }

    /**
     * 根据 ID 删除课程
     * @param id 课程 ID
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return courseService.delete(id);
    }

    /**
     * 添加课程
     * @param course 课程
     * @return 课程
     */
    @PostMapping("")
    public Boolean create(@RequestBody AddCourse course) {
        return courseService.create(course);
    }

    /**
     * 更新课程信息
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable Long id, @RequestBody AddCourse course) {
        return courseService.update(id, course);
    }

    /**
     * 根据 ID 查询任课教师
     * @param id 课程 ID
     * @return 课程
     */
    @GetMapping("/{id}/teachers")
    public List<TeacherResponse> getTeachers(@PathVariable Long id) {
        return teachingService.findTeachesByCourseId(id).stream()
                .map(TeacherResponse::fromTeacher)
                .collect(Collectors.toList());
    }
}
