package com.ispiroglu.dtdemo.repository

import com.ispiroglu.dtdemo.domain.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long> {
    fun existsByCourseCode(courseCode: String): Boolean
}