package com.ispiroglu.dtdemo.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class CourseRegistration(
    @Id val id: UUID? = null,
    @OneToOne
    val course: Course,
    @OneToOne
    val student: Student,
    @CreatedDate val registrationDate: Instant,
    @LastModifiedDate var lastModifiedDate: Instant
)
