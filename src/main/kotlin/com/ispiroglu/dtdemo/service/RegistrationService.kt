package com.ispiroglu.dtdemo.service

import com.ispiroglu.dtdemo.domain.model.CourseRegistration
import com.ispiroglu.dtdemo.repository.CourseRegistrationRepository
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val courseRegistrationRepository: CourseRegistrationRepository,
    private val courseService: CourseService,
    private val studentService: StudentService
) {

    fun getAllRegistrations(): Collection<CourseRegistration> = courseRegistrationRepository.findAll()

    fun registerStudentToCourse(studentId: Long, courseId: Long) {
        val student = studentService.getStudentById(studentId)
        val course = courseService.getCourseById(courseId)

        val registration = CourseRegistration(
            student = student,
            course = course,
            active = true
        )
        courseRegistrationRepository.save(registration)
        student.registeredCourses?.add(registration)
        course.attendanceList?.add(registration)
        studentService.saveStudent(student)
        courseService.saveCourse(course)
    }
}