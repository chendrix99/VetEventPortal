package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val qualifier: String? = null,
    val min: String? = null,
    val unit: String? = null
)