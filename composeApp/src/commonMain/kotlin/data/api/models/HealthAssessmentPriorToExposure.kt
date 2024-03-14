package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class HealthAssessmentPriorToExposure(
    val assessed_by: String? = null,
    val condition: String? = null
)