package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Manufacturer(
    val name: String? = null,
    val registration_number: String? = null
)