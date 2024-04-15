package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope
import ui.screens.composables.GoBackTextTopAppBar
import ui.screens.composables.SavedSearchResultCard
import ui.screens.composables.SimpleTextFAB
import ui.screens.composables.WarningDialog
import ui.viewmodels.SavedSearchesViewModel

//----------------------------------------------------------------------------------------
@Composable
fun SavedSearchesScreen(
    onNav: () -> Unit,
    viewModel: SavedSearchesViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.viewModelScope.launch {
        viewModel.getAllSavedResults()
    }

    val state by viewModel.uiState.collectAsState()
    val savedSearches = state.savedSearches.collectAsState(emptyList())

    var openWarningDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { SavedSearchesTopAppBar(
            onBack = onNav
        ) },
        floatingActionButton = {
            SimpleTextFAB(
                onClick = {
                    openWarningDialog = !openWarningDialog
                },
                title = "Clear"
            )
        }
    ) {
        if (openWarningDialog) {
            WarningDialog(
                viewModel = viewModel,
                message = "Delete All Saved Results?",
                onDismiss = {
                    openWarningDialog = !openWarningDialog
                }
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = savedSearches.value) { result ->
                SavedSearchResultCard(
                    data = result
                )
            }
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun SavedSearchesTopAppBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    GoBackTextTopAppBar(
        title = "Saved Searches",
        onBackClick = onBack
    )
}