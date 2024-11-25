package com.example.exceptionHandling.task

import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
class DummyTaskRepositoryImpl : TaskRepository {
    @Suppress("MagicNumber")
    val tasks = mutableListOf(
        Task(
            id = TaskId("1"),
            title = "task1",
            description = "description1",
            status = Status.TODO,
            createdBy = UserId("1"),
            createdAt = LocalDateTime.of(2024, 11, 25, 15, 16)
        ),
        Task(
            id = TaskId("2"),
            title = "task2",
            description = "description2",
            status = Status.DOING,
            createdBy = UserId("2"),
            assignedTo = UserId("1"),
            createdAt = LocalDateTime.of(2024, 11, 26, 16, 17)
        ),
        Task(
            id = TaskId("3"),
            title = "task3",
            description = "description3",
            status = Status.DONE,
            createdBy = UserId("3"),
            assignedTo = UserId("2"),
            createdAt = LocalDateTime.of(2024, 11, 27, 17, 18)
        ),
    )

    override fun findAll(): List<Task> {
        return tasks
    }

    override fun findById(taskId: TaskId): Task? {
        return tasks.find { it.id == taskId }
    }

    override fun nextIdentifier(): TaskId {
        return TaskId(UUID.randomUUID().toString())
    }

    override fun save(newTask: Task) {
        tasks.add(newTask)
    }
}
