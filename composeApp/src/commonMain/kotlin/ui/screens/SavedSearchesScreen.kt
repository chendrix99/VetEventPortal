package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.screens.composables.GoBackTextTopAppBar
import ui.screens.composables.SimpleTextFAB
import ui.viewmodels.SavedSearchesViewModel

//----------------------------------------------------------------------------------------
@Composable
fun SavedSearchesScreen(
    onNav: () -> Unit,
    viewModel: SavedSearchesViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SavedSearchesTopAppBar(
            onBack = onNav
        ) },
        floatingActionButton = {
            SimpleTextFAB(
                onClick = {},
                title = "Clear"
            )
        }
    ) {
        Column {
            // The saved searches
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