package com.example.exceptionHandling.task

import kiyotakeshi.com.example.generated.openapi.model.TaskAddRequestDto
import kiyotakeshi.com.example.generated.openapi.model.TaskResponseDto
import org.springframework.stereotype.Service

@Service
class TaskUsecase(
    private val taskRepository: TaskRepository
) {
    fun getTasks(): List<Task> {
        return taskRepository.findAll()
    }

    fun getTask(id: String): Task? {
        return taskRepository.findById(TaskId(id))
    }

    fun addTask(request: TaskAddRequestDto): TaskResponseDto {
        val newId = taskRepository.nextIdentifier()
        val newTask = Task.create(
            id = newId,
            title = request.title,
            description = request.description,
            createdBy = UserId(request.userId),
        )
        taskRepository.save(newTask)
        return newTask.toResponse()
    }
}
