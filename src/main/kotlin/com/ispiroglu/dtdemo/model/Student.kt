package com.ispiroglu.dtdemo.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class Student(
    @Id val id: UUID? = null, // neden nullable??
    var name: String,
    var surname: String,
    var schoolNumber: Number,
    @OneToMany var list: List<CourseRegistration> = listOf(),
    @CreatedDate var createdAt: Instant,
    @LastModifiedDate var lastModifiedAt: Instant

)
