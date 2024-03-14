package data.api.models
import kotlinx.serialization.Serializable

@Serializable
data class Animal(
    val age: Age? = null,
    val breed: Breed? = null,
    val gender: String? = null,
    val species: String? = null,
    val weight: Weight? = null,
    val reproductive_status: String? = null,
    val female_animal_physiological_status: String? = null
)