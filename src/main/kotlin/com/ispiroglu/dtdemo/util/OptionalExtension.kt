package com.ispiroglu.dtdemo.util

import java.util.*

class OptionalExtension {
    fun <T> Optional<T>.unwrap(): T? = orElse(null)
}