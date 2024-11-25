package com.example.exceptionHandling.task

import java.time.LocalDateTime

data class Task(
    val id: TaskId,
    var title: String,
    var description: String?,
    var status: Status,
    val createdBy: UserId,
    var assignedTo: UserId? = null,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null
) {
    companion object {
        fun create(
            id: TaskId,
            title: String,
            description: String?,
            createdBy: UserId
        ): Task {
            return Task(
                id = id,
                title = title,
                description = description,
                // 新規作成時は固定
                status = Status.TODO,
                createdAt = LocalDateTime.now(),
                createdBy = createdBy,
            )
        }
    }
}

enum class Status(val value: String) {
    TODO("todo"),
    DOING("doing"),
    DONE("done");

    companion object {
        fun fromValue(value: String): Status {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("invalid status value: $value")
        }
    }
}

data class TaskId(val value: String)

data class UserId(val value: String)
