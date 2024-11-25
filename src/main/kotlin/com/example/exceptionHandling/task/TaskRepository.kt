package com.example.exceptionHandling.task

interface TaskRepository {
    fun findAll(): List<Task>
    fun findById(taskId: TaskId): Task?
    fun nextIdentifier(): TaskId
    fun save(newTask: Task)
}
