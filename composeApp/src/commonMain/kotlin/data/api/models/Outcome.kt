package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Outcome(
    val medical_status: String? = null,
    val number_of_animals_affected: String? = null
)