package com.ispiroglu.dtdemo.service

import com.ispiroglu.dtdemo.domain.dto.request.student.CreateStudentRequest
import com.ispiroglu.dtdemo.domain.dto.request.student.UpdateStudentRequest
import com.ispiroglu.dtdemo.domain.model.Student
import com.ispiroglu.dtdemo.exception.NotFoundException
import com.ispiroglu.dtdemo.repository.StudentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
@Service
class StudentService(private val studentRepository: StudentRepository) {

    private fun getById(id: Long) = studentRepository.findByIdOrNull(id)?: throw NotFoundException(resource = "customer", args = listOf(id))

    fun getAllStudents(): MutableList<Student> = studentRepository.findAll()

    fun getStudentById(id: Long): Student = getById(id)

    fun createStudent(createStudentDto: CreateStudentRequest): Student {
        if (studentRepository.existsBySchoolNumber(createStudentDto.schoolNumber))
            throw IllegalArgumentException()

        val student = Student(
            name = createStudentDto.name,
            surname = createStudentDto.surname,
            schoolNumber = createStudentDto.schoolNumber
        )
        return studentRepository.save(student)
    }


    /*
       Iki istek ayni anda atildiginda nasil isliyor?
     */
     fun updateStudent(id: Long, updateStudentDto: UpdateStudentRequest) {
        with(updateStudentDto) {
            val student = getById(id)
            if (studentRepository.existsBySchoolNumber(schoolNumber))
                throw IllegalArgumentException()
            /*
            * Should check existing all fields and return student object
            * in a different method for better readability
            * */
            student.name = name
            student.surname = surname
            student.schoolNumber = schoolNumber

            studentRepository.save(student)
        }
    }

    fun deleteStudent(id: Long)  {
        if (!studentRepository.existsById(id))
            throw  NotFoundException()
        studentRepository.deleteById(id)
    }

    fun saveStudent(student: Student)  {
        studentRepository.save(student)
    }
}

