package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.SearchResultData
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope
import ui.screens.composables.EmptyResultsDialog
import ui.screens.composables.GoBackTextTopAppBar
import ui.screens.composables.SearchResultAndSaveButtonCard
import ui.viewmodels.AdvancedSearchResultsViewModel
import ui.viewmodels.AdvancedSearchViewModel

//----------------------------------------------------------------------------------------
@Composable
fun AdvancedSearchResultsScreen(
    navBack: () -> Unit,
    viewModel: AdvancedSearchResultsViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.updateResults()
    val state by viewModel.uiState.collectAsState()

    var resultInfo by remember{mutableStateOf(false)}
    var data: SearchResultData by remember { mutableStateOf(SearchResultData()) }

    Scaffold(
        topBar = { AdvancedSearchResultsTopAppBar(
            onBack = {
                viewModel.clearResults()
                navBack()
            }
        ) }
    ) {
        // Lazy list of search results
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
        ) {
            items(items = state.searchResults) { item ->
                SearchResultAndSaveButtonCard(
                    data = item,
                    onSave = {
                        viewModel.viewModelScope.launch {
                            viewModel.saveResult(
                                resultId = item.report_id,
                                animalSpecies = item.animal?.species ?: "Unknown",
                                animalAge = item.animal?.age?.min?.toDouble() ?: 0.0,
                                animalAgeUnit = item.animal?.age?.unit ?: "Unknown",
                                animalBreedComponent = item.animal?.breed?.breed_component.toString(),
                                drugActiveIngredients = item.drug?.get(0)?.active_ingredients?.map { item -> item.name }?.joinToString(),
                                seriousAdverseEvent = item.serious_ae ?: "Unknown"
                            )
                        }
                    },
                    onMore = {
                        resultInfo = true
                        data = item
                    }
                )
            }
        }
    }

    if (state.emptyResults) {
        EmptyResultsDialog(
            onDismiss = navBack
        )
    }

    if (resultInfo) {
        SearchResultInfoScreen(
            onNav = { resultInfo = false },
            onFabClick = {
                viewModel.viewModelScope.launch {
                    viewModel.saveResult(
                        resultId = data.report_id,
                        animalSpecies = data.animal?.species ?: "Unknown",
                        animalAge = data.animal?.age?.min?.toDouble() ?: 0.0,
                        animalAgeUnit = data.animal?.age?.unit ?: "Unknown",
                        animalBreedComponent = data.animal?.breed?.breed_component.toString(),
                        drugActiveIngredients = data.drug?.get(0)?.active_ingredients?.map { item -> item.name }?.joinToString(),
                        seriousAdverseEvent = data.serious_ae ?: "Unknown"
                    )
                }
            },
            data = data
        )
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun AdvancedSearchResultsTopAppBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    GoBackTextTopAppBar(
        title = "Advanced Search Results",
        onBackClick = onBack
    )
}