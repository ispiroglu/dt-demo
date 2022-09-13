package com.ispiroglu.dtdemo.exception

import org.springframework.http.HttpStatus

class NotFoundException : BaseResponseStatusException {
    constructor() : super(status = HttpStatus.NOT_FOUND, code = "NOT_FOUND", messageProperty = "error.*.resource.not-found")
    constructor(errorCode: String, messageProperty: String, args: List<Any> = emptyList(), errors: List<Error> = emptyList()) : super(HttpStatus.NOT_FOUND, errorCode, messageProperty, args, errors)
    constructor(resource: String, errorCode: String = "NOT_FOUND", messageProperty: String = "error.$resource.resource.not-found", args: List<Any> = emptyList(), errors: List<Error> = emptyList()) : super(HttpStatus.NOT_FOUND, errorCode, messageProperty, args, errors)
}
