package com.ispiroglu.dtdemo.controller

import com.ispiroglu.dtdemo.domain.dto.request.registration.RegistrationRequest
import com.ispiroglu.dtdemo.domain.model.CourseRegistration
import com.ispiroglu.dtdemo.service.RegistrationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/registration")
class RegistrationController(private val registrationService: RegistrationService) {

    @PostMapping
    fun registerStudentToCourse(@RequestBody registrationRequest: RegistrationRequest) {
        registrationService.registerStudentToCourse(registrationRequest.studentId, registrationRequest.courseId)
    }

    @GetMapping
    fun getAllRegistrations(): Collection<CourseRegistration> = registrationService.getAllRegistrations()
}