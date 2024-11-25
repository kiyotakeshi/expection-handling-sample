package com.example.exceptionHandling.task

import kiyotakeshi.com.example.generated.openapi.model.TaskResponseDto

fun List<Task>.toResponse(): List<TaskResponseDto> = this.sortedBy { it.id.value }
    .map {
        TaskResponseDto(
            id = it.id.value,
            title = it.title,
            description = it.description,
            status = TaskResponseDto.Status.forValue(it.status.value),
            createdBy = it.createdBy.value,
            assignedTo = it.assignedTo?.value,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt,
        )
    }

fun Task.toResponse(): TaskResponseDto = TaskResponseDto(
    id = this.id.value,
    title = this.title,
    description = this.description,
    status = TaskResponseDto.Status.forValue(this.status.value),
    createdBy = this.createdBy.value,
    assignedTo = this.assignedTo?.value,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)
