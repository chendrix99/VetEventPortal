package data

import data.api.models.SearchResultApi

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