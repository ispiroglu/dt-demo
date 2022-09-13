package com.ispiroglu.dtdemo.controller

import com.ispiroglu.dtdemo.domain.dto.request.student.CreateStudentRequest
import com.ispiroglu.dtdemo.domain.dto.request.student.UpdateStudentRequest
import com.ispiroglu.dtdemo.domain.model.Student
import com.ispiroglu.dtdemo.service.StudentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/student")
class StudentController(private val studentService: StudentService) {
    @GetMapping
    fun getAllStudents(): List<Student> = studentService.getAllStudents()

    @PostMapping
    fun saveStudent(@RequestBody createStudentRequest: CreateStudentRequest) {
        studentService.createStudent(createStudentRequest)
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long) = studentService.getStudentById(id)

    @PatchMapping("/{id}")
    fun updateStudentById(@PathVariable id: Long, @RequestBody updateStudentRequest: UpdateStudentRequest)
        = studentService.updateStudent(id, updateStudentRequest)

    @DeleteMapping("/{id}")
    fun deleteStudentById(@PathVariable id: Long) = studentService.deleteStudent(id)
}