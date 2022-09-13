package com.ispiroglu.dtdemo.repository

import com.ispiroglu.dtdemo.domain.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, Long>{
//    suspend fun findById(id: UUID, cacheFirst: Boolean): Student?

    fun existsBySchoolNumber(schoolNumber: Int): Boolean
    fun findStudentBySchoolNumber(schoolNumber: Int) : Student?
}