package com.ispiroglu.dtdemo.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.PersistenceCreator
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class Student @PersistenceCreator constructor(
    @Id @GeneratedValue val id: Long? = null,
    var name: String,
    var surname: String,
    var schoolNumber: Int,
    @OneToMany @JsonIgnore var registeredCourses: MutableList<CourseRegistration>? = null,
    @CreatedDate var createdAt: Instant? = null,
    @LastModifiedDate var lastModifiedAt: Instant? = null

)
