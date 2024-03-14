package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.screens.composables.GoBackTextTopAppBar

//----------------------------------------------------------------------------------------
@Composable
fun AdvancedSearchResultsScreen(
    navBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { AdvancedSearchResultsTopAppBar(
            onBack = navBack
        ) }
    ) {
        Column {
            // The search results, prob in LazyColumn
        }
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