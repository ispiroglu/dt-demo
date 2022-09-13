package com.ispiroglu.dtdemo.domain.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.ToStringExclude
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Course(
    @Id @GeneratedValue val id : Long? = null,
    var courseCode : String?,
    var name: String?,

    @OneToMany
    @JsonIgnore
    var attendanceList: MutableList<CourseRegistration>? = mutableListOf(),
    @CreatedDate var createdAt: Instant? = null,
    @LastModifiedDate var lastModifiedAt: Instant? = null
)
