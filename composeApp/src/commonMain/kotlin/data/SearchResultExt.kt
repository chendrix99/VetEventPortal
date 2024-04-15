package data

import com.example.vetfdaportal.Result
import data.api.models.ActiveIngredient
import data.api.models.Age
import data.api.models.Animal
import data.api.models.Breed
import data.api.models.Drug
import data.api.models.SearchResultApi
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

fun SearchResultApi.toDataModel() = SearchResultData(
    animal = animal,
    drug = drug,
    health_assessment_prior_to_exposure = health_assessment_prior_to_exposure,
    number_of_animals_affected = number_of_animals_affected,
    number_of_animals_treated = number_of_animals_treated,
    onset_date = onset_date,
    original_receive_date = original_receive_date,
    outcome = outcome,
    primary_reporter = primary_reporter,
    reaction = reaction,
    receiver = receiver,
    report_id = report_id,
    serious_ae = serious_ae,
    treated_for_ae = treated_for_ae,
    type_of_information = type_of_information,
    unique_aer_id_number = unique_aer_id_number,
    duration = duration,
    secondary_reporter = secondary_reporter,
    time_between_exposure_and_onset = time_between_exposure_and_onset
)

fun SearchResultData.toApiModel() = SearchResultApi(
    animal = animal,
    drug = drug,
    health_assessment_prior_to_exposure = health_assessment_prior_to_exposure,
    number_of_animals_affected = number_of_animals_affected,
    number_of_animals_treated = number_of_animals_treated,
    onset_date = onset_date,
    original_receive_date = original_receive_date,
    outcome = outcome,
    primary_reporter = primary_reporter,
    reaction = reaction,
    receiver = receiver,
    report_id = report_id,
    serious_ae = serious_ae,
    treated_for_ae = treated_for_ae,
    type_of_information = type_of_information,
    unique_aer_id_number = unique_aer_id_number,
    duration = duration,
    secondary_reporter = secondary_reporter,
    time_between_exposure_and_onset = time_between_exposure_and_onset
)

fun Result.toDataModel() = SearchResultData(
    report_id = report_id,
    animal = Animal(
        age = Age(
            min = animal_age.toString(),
            unit = animal_age_unit
        ),
        species = animal_species,
        breed = Breed(
            breed_component = JsonPrimitive(animal_breed_component)
        ),
    ),
    drug = listOf(
      Drug(
          active_ingredients = drug_active_ingredients?.split(",")?.map {
              ActiveIngredient(name = it)
          }
      )
    ),
    serious_ae = serious_adverse_event
)