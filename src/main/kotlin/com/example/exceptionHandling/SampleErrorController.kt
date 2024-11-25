package com.example.exceptionHandling

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class SampleErrorController {

    @GetMapping("/problem-detail")
    fun problemDetail(): ProblemDetail {
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR).apply {
            title = "problem detail title"
            detail = "problem detail"
            properties = mapOf(
                "custom_property1" to "hoge",
                "custom_property2" to "fuga",
            )
        }
    }

    @Suppress("MagicNumber")
    @GetMapping("/runtime-exception")
    fun runtimeException() {
        val numbers = intArrayOf(1, 2, 3)
        println(numbers[3])
    }
}
