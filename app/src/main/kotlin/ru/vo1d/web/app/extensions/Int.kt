package ru.vo1d.web.app.extensions

import io.ktor.server.plugins.*

fun Int.failIfNegative() = apply {
    if (this < 0) throw BadRequestException("Request parameter is less than zero")
}