package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class OffLabelUse(
    val off_label_use: List<String>? = null
)