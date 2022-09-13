package com.ispiroglu.dtdemo.service

import com.ispiroglu.dtdemo.domain.dto.request.student.CreateStudentRequest
import com.ispiroglu.dtdemo.domain.dto.request.student.UpdateStudentRequest
import com.ispiroglu.dtdemo.domain.model.Student
import com.ispiroglu.dtdemo.exception.NotFoundException
import com.ispiroglu.dtdemo.repository.StudentRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class StudentServiceTests {
    private val studentRepository: StudentRepository = mockk<StudentRepository>(relaxed = true)
    private val studentService = StudentService(studentRepository)

    @Test
    fun `when getAllStudents is called, it should correctly call findAll from its Repo`() {
        // given
        val sampleStudent = Student(1, "Sample name", "Sample surname", 4)
        every { studentRepository.findAll() } returns listOf(sampleStudent)

        // when
        studentService.getAllStudents()

        // then
        verify(exactly = 1) { studentRepository.findAll() }
    }
    
    @Test
    fun `when getStudent is called, it should correctly return the requested student`() {
        // given
        val id = 1L
        val sampleStudent = Student(id, "Sample name", "Sample surname", 4)

        every { studentRepository.findByIdOrNull(id) } returns sampleStudent
        
        //when
        studentService.getStudentById(id)
        
        //then
        verify (exactly = 1) { studentRepository.findByIdOrNull(sampleStudent.id) }
        assertEquals(studentService.getStudentById(sampleStudent.id!!), studentRepository.findByIdOrNull(sampleStudent.id))
    }

    @Test
    fun `when getStudent is called with invalid id, it should throw NotFoundException`() {
        // given
        val sampleStudent = Student(1, "Sample name", "Sample surname", 4)
        every { studentRepository.findByIdOrNull(sampleStudent.id) } returns null

        // when - then
        assertThrows(NotFoundException::class.java) {
            studentService.getStudentById(sampleStudent.id!!)
        }
    }
    
    @Test
    fun `when createStudent is called with an already existing studentId, it should throw IllegalArgumentException `() {
        // given
        val sampleRequest = CreateStudentRequest( "Sample name", "Sample surname", 4)
        val sampleStudent = Student(1,  "Sample name", "Sample surname", 4)
        every { studentRepository.existsBySchoolNumber(sampleRequest.schoolNumber) } returns true

        //when - then
        assertThrows(IllegalArgumentException::class.java) {
            studentService.createStudent(sampleRequest)
        }
    }

    @Test
    fun `when updateStudent is called with an invalid student id, it should throw NotFoundException`() {
        // given
        val sampleStudent = Student(1,  "Sample name", "Sample surname", 4)
        val sampleRequest = UpdateStudentRequest( "Sample name", "Sample surname", 4)
        every { studentRepository.findByIdOrNull(sampleStudent.id) } returns null

        //when - then
        assertThrows(NotFoundException::class.java) {
            studentService.updateStudent(sampleStudent.id!!, sampleRequest)
        }
    }

    @Test
    fun `when updateStudent is called with an existing school number, it should throw IllegalArgumentException`() {
        // given
        val sampleStudent = Student(1,  "Sample name", "Sample surname", 4)
        val sampleRequest = UpdateStudentRequest( "Sample name", "Sample surname", 4)
        every { studentRepository.findByIdOrNull(sampleStudent.id) } returns sampleStudent
        every { studentRepository.existsBySchoolNumber(sampleStudent.schoolNumber) } returns true

        //when - then
        assertThrows(IllegalArgumentException::class.java) {
            studentService.updateStudent(sampleStudent.id!!, sampleRequest)
        }

    }

    @Test
    fun `when deleteStudent is called with an invalid student id, it should throw NotFoundException`() {
        // given
        val sampleStudent = Student(1,  "Sample name", "Sample surname", 4)
        every { studentRepository.existsById(sampleStudent.id!!) } returns false

        //when - then
        assertThrows(NotFoundException::class.java) {
            studentService.deleteStudent(sampleStudent.id!!)
        }

    }
}