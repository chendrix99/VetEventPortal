package data.api.models
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Drug (
    val active_ingredients: List<ActiveIngredient>? = null,
    val administered_by: String? = null,
    val atc_vet_code: String? = null,
    val brand_name: String? = null,
    val dosage_form: String? = null,
    val dose: DoseX? = null,
    val first_exposure_date: String? = null,
    val last_exposure_date: String? = null,
    val manufacturer: Manufacturer? = null,
    val route: String? = null,
    val used_according_to_label: String? = null,
    val previous_exposure_to_drug: String? = null,
    val lot_number: String? = null,
    val frequency_of_administration: FrequencyOfAdmin? = null,
    val previous_ae_to_drug: String? = null,
    val ae_abated_after_stopping_drug: String? = null,
    val ae_reappeared_after_resuming_drug: String? = null,
    val lot_expiration: String? = null,
    val off_label_use: JsonElement? = null,
    val product_ndc: String? = null,
)