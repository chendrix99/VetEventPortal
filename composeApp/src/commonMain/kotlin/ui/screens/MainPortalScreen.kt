package ui.screens

import Disclaimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
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
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModelScope
import ui.screens.composables.CircularLoadingScreen
import ui.screens.composables.DisclaimerDialog
import ui.screens.composables.EmptyResultsDialog
import ui.screens.composables.GeneralSearchBar
import ui.screens.composables.SearchResultAndSaveButtonCard
import ui.screens.composables.SimpleTextTopAppBar
import ui.viewmodels.MainPortalViewModel

//----------------------------------------------------------------------------------------
@Composable
fun MainPortalScreen(
    navigator: Navigator,
    viewModel: MainPortalViewModel,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    val state by viewModel.uiState.collectAsState()

    var resultInfo by remember{ mutableStateOf(false) }
    var data: SearchResultData by remember { mutableStateOf(SearchResultData()) }

    var loading by remember { mutableStateOf(false) }

    var disclaimer by remember{ mutableStateOf(Disclaimer.disclaimerShown) }

    Scaffold(
        topBar = { MainPortalTopAppBar() },
        bottomBar = { MainPortalBottomAppBar(navigator) }
    ) {
        Column(
            modifier = modifier
        ) {
            // SearchSection
            GeneralSearchBar(
                value = state.searchString,
                title = "Search drugs or animals...",
                onValueChange = {
                    viewModel.updateSearchString(it)
                },
                onSearch = {
                    viewModel.viewModelScope.launch {
                        loading = true
                        val results = viewModel.performSearch(state.searchString)
                        viewModel.updateEmptyResults(results.isEmpty())
                        loading = false
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Divider(
                thickness = 2.dp
            )

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
    }

    if (loading) {
        CircularLoadingScreen()
    }

    if (state.emptyResults) {
        EmptyResultsDialog(
            onDismiss = {
                viewModel.updateEmptyResults(false)
            }
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

    if (!disclaimer) {
        DisclaimerDialog(
            onDismiss = {
                Disclaimer.disclaimerShown = true
                disclaimer = true
            }
        )
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun MainPortalTopAppBar(
    modifier: Modifier = Modifier
) {
    SimpleTextTopAppBar(
        title = "Vet Event Portal",
    )
}

//----------------------------------------------------------------------------------------
@Composable
fun MainPortalBottomAppBar(
    navigator: Navigator,
    modifier: Modifier = Modifier
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = false,
            onClick = { navigator.navigate("/saved") },
            icon = { Icon(Icons.Default.Star, null) },
            label = { Text(text = "Saved") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navigator.navigate("/advanced") },
            icon = { Icon(Icons.Default.Build, null) },
            label = { Text(text = "Advanced") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navigator.navigate("/special") },
            icon = { Icon(Icons.Default.Info, null) },
            label = { Text(text = "Special") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navigator.navigate("/feature") },
            icon = { Icon(Icons.Default.Create, null) },
            label = { Text(text = "Request Feature") }
        )
    }
}