package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Results(
    val limit: Int,
    val skip: Int,
    val total: Int
)