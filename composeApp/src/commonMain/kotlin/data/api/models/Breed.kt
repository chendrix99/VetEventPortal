package data.api.models
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Breed(
    val breed_component: JsonElement? = null,
    val is_crossbred: String? = null
)