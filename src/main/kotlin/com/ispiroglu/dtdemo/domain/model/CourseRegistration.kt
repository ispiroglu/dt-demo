package com.ispiroglu.dtdemo.domain.model

import org.apache.commons.lang3.builder.ToStringExclude
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
data class CourseRegistration(
    @Id @GeneratedValue val id: Long? = null,
    @ManyToOne     @ToStringExclude
    val course: Course,
    @ManyToOne     @ToStringExclude
    val student: Student,
    val successRate: Double? = null,
    val active: Boolean? = null,
    @CreatedDate val registrationDate: Instant? = null,
    @LastModifiedDate var lastModifiedDate: Instant? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CourseRegistration

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , successRate = $successRate , active = $active , registrationDate = $registrationDate , lastModifiedDate = $lastModifiedDate )"
    }
}
