package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class FrequencyOfAdmin (
    val value: String? = null,
    val unit: String? = null
)