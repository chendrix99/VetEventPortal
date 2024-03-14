package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Reaction(
    val accuracy: String? = null,
    val number_of_animals_affected: String? = null,
    val veddra_term_code: String? = null,
    val veddra_term_name: String? = null,
    val veddra_version: String? = null
)