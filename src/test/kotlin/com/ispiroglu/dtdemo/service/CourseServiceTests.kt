package com.ispiroglu.dtdemo.service

import com.ispiroglu.dtdemo.domain.dto.request.course.CreateCourseRequest
import com.ispiroglu.dtdemo.domain.model.Course
import com.ispiroglu.dtdemo.exception.NotFoundException
import com.ispiroglu.dtdemo.repository.CourseRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class CourseServiceTests {
    private val courseRepository = mockk<CourseRepository>(relaxed = true)
    private val courseService = CourseService(courseRepository)

    @Test
    fun `when getAllCourses is called, it should correctly call findAll from it's data source `() {
        // given
        val sampleCourse = Course( 1, "BLM-3031", "Probability" )
        every { courseRepository.findAll() } returns mutableListOf(sampleCourse)
        
        //when
        courseService.getAllCourses()
        
        //then
        verify (exactly = 1) { courseRepository.findAll() }
    }

    @Test
    fun `when getCourseById is called, it should return the requested course`() {
        // given
        val sampleCourse = Course( 1, "BLM-3031", "Probability" )
        every { courseRepository.findByIdOrNull(sampleCourse.id) } returns sampleCourse
        //when
        val returnedCourse = courseService.getCourseById(sampleCourse.id!!)
        //then
        verify (exactly = 1){ courseRepository.findByIdOrNull(sampleCourse.id) }
        assertEquals(returnedCourse, sampleCourse)
    }

    @Test
    fun `when getCourseById is called with an invalid id, it should throw NotFoundException`() {
        // given
        val invalidId = 1231L
        every { courseRepository.findByIdOrNull(invalidId) } returns null

        //when - then
        assertThrows(NotFoundException::class.java) {
            courseService.getCourseById(invalidId)
        }
    }

    @Test
    fun `when createStudent is called with an already existing course code, it should throw IllegalArgumentException`() {
        // given
        val sampleRequest = CreateCourseRequest("Probability", "BLM-3031")
        val sampleCourse = Course( 1, "BLM-3031", "Probability" )

        every { courseRepository.existsByCourseCode(sampleCourse.courseCode!!) } returns true

        //when - then
        assertThrows(IllegalArgumentException::class.java) {
            courseService.createCourse(sampleRequest)
        }
    }

    @Test
    fun `when updateCourse is called with an invalid course id, it should throw NotFoundException `() {
        // given
        val sampleRequest = CreateCourseRequest("Probability", "BLM-3031")
        val sampleCourse = Course( 1, "BLM-3031", "Probability" )
        every { courseRepository.findByIdOrNull(sampleCourse.id) } returns null

        //when - then
        assertThrows(NotFoundException::class.java) {
            courseService.updateCourse(sampleCourse.id!!, sampleRequest)
        }
    }

    @Test
    fun `when updateCourse is called with an existing course code, it should throw IllegalArgumentException`() {
        // given
        val sampleRequest = CreateCourseRequest("Probability", "BLM-3031")
        val sampleCourse = Course( 1, "BLM-3031", "Probability" )
        every { courseRepository.findByIdOrNull(sampleCourse.id) } returns sampleCourse
        every { courseRepository.existsByCourseCode(sampleCourse.courseCode!!) } returns true

        //when - then
        assertThrows(IllegalArgumentException::class.java) {
            courseService.updateCourse(sampleCourse.id!!, sampleRequest)
        }
    }

    @Test
    fun `when deleteCourse is called with an invalid course id, it should throw NotFoundException`() {
        // given
        val sampleCourse = Course( 1, "BLM-3031", "Probability" )
        every { courseRepository.existsById(sampleCourse.id!!) } returns false

        // when  - then
        assertThrows(NotFoundException::class.java) {
            courseService.deleteCourse(sampleCourse.id!!)
        }

    }
}