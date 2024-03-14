package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class ActiveIngredient(
    val dose: DoseX? = null,
    val name: String? = null
)