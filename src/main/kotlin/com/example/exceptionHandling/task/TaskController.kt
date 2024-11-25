package com.example.exceptionHandling.task

import jakarta.validation.Valid
import kiyotakeshi.com.example.generated.openapi.model.TaskAddRequestDto
import kiyotakeshi.com.example.generated.openapi.model.TaskResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val taskUsecase: TaskUsecase
) {
    @GetMapping
    fun getTasks(): ResponseEntity<List<TaskResponseDto>> {
        val tasks = taskUsecase.getTasks()
        return ResponseEntity.ok(tasks.toResponse())
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: String): ResponseEntity<TaskResponseDto> {
        val task = taskUsecase.getTask(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(task.toResponse())
    }

    @PostMapping
    fun add(
        @RequestBody @Valid request: TaskAddRequestDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Void> {
        val addTask = taskUsecase.addTask(request)
        val locationUri = uriBuilder
            .path("/tasks/{id}")
            .buildAndExpand(addTask.id)
            .toUri()

        return ResponseEntity.created(locationUri).build()
    }
}
