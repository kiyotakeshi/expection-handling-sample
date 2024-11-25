package com.example.exceptionHandling.config

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    var logger = LoggerFactory.getLogger(javaClass)

    private fun FieldError.toErrorDetail() =
        ValidationErrorDetail(
            field = this.field,
            rejectedValue = this.rejectedValue,
            code = this.code,
            objectName = this.objectName,
            message = this.defaultMessage
        )

    data class ValidationErrorDetail(
        val field: String,
        val rejectedValue: Any?,
        val code: String?,
        val objectName: String,
        val message: String?
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ProblemDetail {
        logger.error("exception occurs", ex)
        val errors = ex.bindingResult.fieldErrors.map { it.toErrorDetail() }

        return ProblemDetail.forStatus(HttpStatus.BAD_REQUEST).apply {
            detail = ex.message
            setProperty("errors", errors)
        }
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException::class)
    fun handleArrayIndexOutOfBoundsException(ex: ArrayIndexOutOfBoundsException): ProblemDetail {
        logger.error("exception occurs", ex)
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR).apply {
            detail = ex.message
        }
    }
}
