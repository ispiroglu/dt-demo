package com.ispiroglu.dtdemo.service

import com.ispiroglu.dtdemo.domain.model.Course
import com.ispiroglu.dtdemo.domain.model.CourseRegistration
import com.ispiroglu.dtdemo.domain.model.Student
import com.ispiroglu.dtdemo.repository.CourseRegistrationRepository
import com.ispiroglu.dtdemo.repository.CourseRepository
import com.ispiroglu.dtdemo.repository.StudentRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class RegistrationServiceTests {
    private val studentRepository: StudentRepository = mockk<StudentRepository>(relaxed = true)
    private val studentService = StudentService(studentRepository)

    private val courseRepository = mockk<CourseRepository>(relaxed = true)
    private val courseService = CourseService(courseRepository)

    private val courseRegistrationRepository = mockk<CourseRegistrationRepository>(relaxed = true)
    private val registrationService = RegistrationService(courseRegistrationRepository, courseService, studentService)

    @Test
    fun `when registerStudentToCourse is called, it should add registrations, update course and students and save`() {
        // given
        val student = Student(1, "Evren", "Ispir",5, mutableListOf())
        val course = Course(1, "BLM-3031", "Probability", mutableListOf())
        val registration = CourseRegistration(course = course, student =  student, active = true)

        every { studentRepository.findByIdOrNull(student.id) } returns student
        every { courseRepository.findByIdOrNull(course.id) } returns course
        every { courseRegistrationRepository.save(ofType()) } returns registration
        every { studentRepository.save(ofType()) } returns student
        every { courseRepository.save(ofType()) } returns course

        //when
        registrationService.registerStudentToCourse(student.id!!, course.id!!)

        //then
        verify(exactly = 1) {studentRepository.save(student)}
        verify(exactly = 1) {courseRepository.save(course)}
        assertEquals(1, student.registeredCourses!!.size)
        assertEquals(1, course.attendanceList!!.size)
    }

    @Test
    fun `when getAllRegistrations is called, it should call findAll from its datasource`() {
        // given
        val student = Student(1, "Evren", "Ispir",5, mutableListOf())
        val course = Course(1, "BLM-3031", "Probability", mutableListOf())
        val sampleRegistration = CourseRegistration(course = course, student =  student, active = true)
        every { courseRegistrationRepository.findAll() } returns listOf(sampleRegistration)

        //when
        registrationService.getAllRegistrations()

        //then
        verify (exactly = 1) { courseRegistrationRepository.findAll()  }
    }
}