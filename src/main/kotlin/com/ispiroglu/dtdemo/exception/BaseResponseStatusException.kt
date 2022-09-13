package com.ispiroglu.dtdemo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@Suppress("MemberVisibilityCanBePrivate")
open class BaseResponseStatusException : ResponseStatusException {
    val code: String
    val errors: List<Error>
    val messageProperty: String?
    val messageArgs: List<Any>

    constructor(
        status: HttpStatus,
        code: String,
        messageProperty: String,
        messageArgs: List<Any> = emptyList(),
        errors: List<Error> = emptyList()
    ) : super(status) {
        this.code = code
        this.errors = errors
        this.messageProperty = messageProperty
        this.messageArgs = messageArgs
    }

    constructor(
        status: HttpStatus,
        code: String,
        message: String,
        errors: List<Error> = emptyList()
    ) : super(status, message) {
        this.code = code
        this.errors = errors
        this.messageProperty = null
        this.messageArgs = emptyList()
    }
}

@Suppress("MemberVisibilityCanBePrivate")
class Error {
    val code: String
    val message: String?
    val messageProperty: String?
    val messageArgs: List<Any>

    constructor(
        code: String,
        messageProperty: String,
        messageArgs: List<Any> = emptyList(),
    ) {
        this.code = code
        this.messageProperty = messageProperty
        this.messageArgs = messageArgs
        this.message = null
    }

    constructor(
        code: String,
        message: String,
    ) {
        this.code = code
        this.message = message
        this.messageProperty = null
        this.messageArgs = emptyList()
    }
}