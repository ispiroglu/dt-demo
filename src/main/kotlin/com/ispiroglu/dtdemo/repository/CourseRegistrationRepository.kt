package com.ispiroglu.dtdemo.repository

import com.ispiroglu.dtdemo.domain.model.CourseRegistration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRegistrationRepository : JpaRepository<CourseRegistration, String> {
}