package kiyotakeshi.com.example.generated.openapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import jakarta.validation.Valid

/**
 * 
 * @param id 
 * @param title 
 * @param status 
 * @param createdBy 
 * @param createdAt 
 * @param description 
 * @param assignedTo 
 * @param updatedAt 
 */
data class TaskResponseDto(

    @get:JsonProperty("id", required = true) val id: kotlin.String,

    @get:JsonProperty("title", required = true) val title: kotlin.String,

    @get:JsonProperty("status", required = true) val status: TaskResponseDto.Status,

    @get:JsonProperty("createdBy", required = true) val createdBy: kotlin.String,

    @get:JsonProperty("createdAt", required = true) val createdAt: java.time.LocalDateTime,

    @get:JsonProperty("description") val description: kotlin.String? = null,

    @get:JsonProperty("assignedTo") val assignedTo: kotlin.String? = null,

    @get:JsonProperty("updatedAt") val updatedAt: java.time.LocalDateTime? = null
    ) {

    /**
    * 
    * Values: todo,doing,done
    */
    enum class Status(@get:JsonValue val value: kotlin.String) {

        todo("todo"),
        doing("doing"),
        done("done");

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.String): Status {
                return values().first{it -> it.value == value}
            }
        }
    }

}

