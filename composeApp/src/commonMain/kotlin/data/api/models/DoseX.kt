package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class DoseX(
    val denominator: String? = null,
    val denominator_unit: String? = null,
    val numerator: String? = null,
    val numerator_unit: String? = null
)