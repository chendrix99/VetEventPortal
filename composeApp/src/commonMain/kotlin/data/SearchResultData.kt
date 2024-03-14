package data

import data.api.models.Animal
import data.api.models.Drug
import data.api.models.Duration
import data.api.models.HealthAssessmentPriorToExposure
import data.api.models.Outcome
import data.api.models.Reaction
import data.api.models.Receiver

data class SearchResultData(
    val animal: Animal? = null,
    val drug: List<Drug>? = null,
    val health_assessment_prior_to_exposure: HealthAssessmentPriorToExposure? = null,
    val number_of_animals_affected: String? = null,
    val number_of_animals_treated: String? = null,
    val onset_date: String? = null,
    val original_receive_date: String? = null,
    val outcome: List<Outcome>? = null,
    val primary_reporter: String? = null,
    val reaction: List<Reaction>? = null,
    val receiver: Receiver? = null,
    val report_id: String? = null,
    val serious_ae: String? = null,
    val treated_for_ae: String? = null,
    val type_of_information: String? = null,
    val unique_aer_id_number: String? = null,
    val duration: Duration? = null,
    val secondary_reporter: String? = null,
    val time_between_exposure_and_onset: String? = null,
)