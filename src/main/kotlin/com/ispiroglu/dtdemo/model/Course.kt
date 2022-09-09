package com.ispiroglu.dtdemo.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Course(
    @Id val id : UUID? = null,
    var courseCode : String,
    var name: String,
    @OneToMany var list: List<CourseRegistration> = listOf(),
    @CreatedDate var createdAt: Instant,
    @LastModifiedDate var lastModifiedAt: Instant
)
