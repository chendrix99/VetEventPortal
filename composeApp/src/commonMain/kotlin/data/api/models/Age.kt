package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Age(
    val qualifier: String? = null,
    val min: String? = null,
    val unit: String? = null
)