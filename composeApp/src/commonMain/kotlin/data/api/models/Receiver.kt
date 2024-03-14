package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Receiver(
    val city: String? = null,
    val country: String? = null,
    val organization: String? = null,
    val postal_code: String? = null,
    val state: String? = null,
    val street_address: String? = null
)