package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.screens.composables.GoBackTextTopAppBar
import ui.screens.composables.SimpleTextFAB

//----------------------------------------------------------------------------------------
@Composable
fun SearchResultInfoScreen(
    onNav: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SearchResultInfoTopAppBar(
            onBack = onNav
        ) },
        floatingActionButton = {
            SimpleTextFAB(
                onClick = {},
                title = "Save"
            )
        }
    ) {
        Column {
            //The Result Info
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