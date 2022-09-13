package com.ispiroglu.dtdemo.controller

import com.ispiroglu.dtdemo.domain.dto.request.course.CreateCourseRequest
import com.ispiroglu.dtdemo.domain.model.Course
import com.ispiroglu.dtdemo.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/course")
class CourseController(private val courseService: CourseService) {
    @GetMapping
    fun getAllCourses(): List<Course> = courseService.getAllCourses()

    @PostMapping
    fun saveStudent(@RequestBody createCourseRequest: CreateCourseRequest) {
        courseService.createCourse(createCourseRequest)
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long) = courseService.getCourseById(id)

    @PatchMapping("/{id}")
    fun updateStudentById(@PathVariable id: Long, @RequestBody createCourseRequest: CreateCourseRequest)
            = courseService.updateCourse(id, createCourseRequest)

    @DeleteMapping("/{id}")
    fun deleteStudentById(@PathVariable id: Long) = courseService.deleteCourse(id)
}