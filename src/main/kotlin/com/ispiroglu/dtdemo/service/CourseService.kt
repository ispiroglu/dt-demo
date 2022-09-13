package com.ispiroglu.dtdemo.service

import com.ispiroglu.dtdemo.domain.dto.request.course.CreateCourseRequest
import com.ispiroglu.dtdemo.domain.model.Course
import com.ispiroglu.dtdemo.repository.CourseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository) {
    private fun getById(id: Long) = courseRepository.findByIdOrNull(id) ?: throw com.ispiroglu.dtdemo.exception.NotFoundException()

    fun getAllCourses(): MutableList<Course> = courseRepository.findAll()

    fun getCourseById(id: Long): Course = getById(id)

    fun createCourse(createCourseRequest: CreateCourseRequest): Course {
        if (courseRepository.existsByCourseCode(createCourseRequest.courseCode))
            throw IllegalArgumentException()
        val course = Course(
            name = createCourseRequest.name,
            courseCode = createCourseRequest.courseCode
        )
        return courseRepository.save(course)
    }

    fun updateCourse(id: Long, updateCourseRequest: CreateCourseRequest) {
        val course = getById(id)
        if (courseRepository.existsByCourseCode(updateCourseRequest.courseCode))
            throw IllegalArgumentException()

        /**
         * should create course object after validations at other method
         * for better readability
         */

        course.name = updateCourseRequest.name
        course.courseCode = updateCourseRequest.courseCode

        courseRepository.save(course)
    }

    fun deleteCourse(id: Long) {
        if (!courseRepository.existsById(id))
            throw com.ispiroglu.dtdemo.exception.NotFoundException()
        courseRepository.deleteById(id)
    }

    fun saveCourse(course: Course) = courseRepository.save(course)
}