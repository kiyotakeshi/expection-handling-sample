package com.example.exceptionHandling

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExceptionHandlingApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<ExceptionHandlingApplication>(*args)
}
