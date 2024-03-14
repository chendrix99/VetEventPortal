package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val disclaimer: String,
    val last_updated: String,
    val license: String,
    val results: Results,
    val terms: String
)