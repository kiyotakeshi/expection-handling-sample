package kiyotakeshi.com.example.generated.openapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param userId 
 * @param title 
 * @param description 
 */
data class TaskAddRequestDto(

    @get:JsonProperty("userId", required = true) val userId: kotlin.String,

    @get:Size(min=3,max=50)
    @get:JsonProperty("title", required = true) val title: kotlin.String,

    @get:Size(min=1,max=1000)
    @get:JsonProperty("description") val description: kotlin.String? = null
    ) {

}

