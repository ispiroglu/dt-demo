package com.ispiroglu.dtdemo.domain.dto.request.student

data class CreateStudentRequest(
    val name: String,
    val surname: String,
    val schoolNumber: Int
)
