package ui.screens

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ResistanceConfig
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.SearchResultData
import ui.screens.composables.GoBackTextTopAppBar
import ui.screens.composables.SimpleTextFAB

//----------------------------------------------------------------------------------------
@Composable
fun SearchResultInfoScreen(
    data: SearchResultData,
    onNav: () -> Unit,
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { SearchResultInfoTopAppBar(
            onBack = onNav
        ) },
        floatingActionButton = {
            SimpleTextFAB(
                onClick = onFabClick,
                title = "Save"
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(6.dp)
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState,
                    flingBehavior = ScrollableDefaults.flingBehavior()
                )
        ) {
            Text(
                text = "Animal Information -",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Text(text = "Species: " + (data.animal?.species ?: "Unknown"), fontSize = 12.sp)
            Text(text = "Age: " + (data.animal?.age?.min ?: "Unknown age") + " " + (data.animal?.age?.unit ?: "Unknown length"), fontSize = 12.sp)
            Text(text = "Breed: " + if (data.animal?.breed?.breed_component.toString() == "null") "Unknown"
                else data.animal?.breed?.breed_component.toString(), fontSize = 12.sp)
            Text(text = "Gender: ${data.animal?.gender ?: "Unknown"}", fontSize = 12.sp)
            Text(text = "Weight: ${data.animal?.weight?.min ?: "Unknown"} ${data.animal?.weight?.unit ?: ""}", fontSize = 12.sp)
            Text(text = "Reproductive Status: ${data.animal?.reproductive_status ?: "Unknown"}", fontSize = 12.sp)
            Text(text = "Serious Adverse Event: ${data.serious_ae ?: "Unknown"}", fontSize = 12.sp)

            Divider(thickness = 2.dp)

            if (!data.drug.isNullOrEmpty()) {
                Text(
                    text = "Drug Information -",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                for ((drugCount, drug) in data.drug.withIndex()) {
                    Text(text = "Drug ${drugCount + 1}:", fontWeight = FontWeight.Bold, fontSize = 12.sp)

                    val ingredients = mutableListOf<String>()
                    drug.active_ingredients?.forEach {
                        if (it.name != null) {
                            ingredients.add(it.name)
                        }
                    }

                    Text(text = "Active Ingredients: ${ingredients.joinToString()}", fontSize = 12.sp)

                    Text(text = "Previous Exposure to Drug: ${drug.previous_exposure_to_drug ?: "Unknown"}", fontSize = 12.sp)
                    Text(text = "Previous Adverse Event With Drug: ${drug.previous_ae_to_drug ?: "Unknown"}", fontSize = 12.sp)

                    Text(text = "Route Drug Administered: ${drug.route ?: "Unknown"}", fontSize = 12.sp)
                    Text(text = "Drug Administered By: ${drug.administered_by ?: "Unknown"}", fontSize = 12.sp)

                    Text(text = "Dose: ${drug.dose?.numerator ?: "Unknown"} ${drug.dose?.numerator_unit ?: ""}" +
                            " / ${drug.dose?.denominator ?: "Unknown"} ${drug.dose?.denominator_unit ?: ""}", fontSize = 12.sp)
                    Text(text = "Frequency of Administration: ${drug.frequency_of_administration?.value ?: "Unknown"}" +
                            " ${drug.frequency_of_administration?.unit ?: ""}", fontSize = 12.sp)
                    Text(text = "Last Exposure Date: ${drug.last_exposure_date ?: "Unknown"}", fontSize = 12.sp)

                    Divider()
                }
            }

            if (!data.outcome.isNullOrEmpty()) {
                Text(
                    text = "Outcomes -",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                data.outcome.forEach {
                    Text(text = "Medical Status: ${it.medical_status ?: "Unknown"}", fontSize = 12.sp)
                }
            }
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun SearchResultInfoTopAppBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    GoBackTextTopAppBar(
        title = "Result Info",
        onBackClick = onBack
    )
}