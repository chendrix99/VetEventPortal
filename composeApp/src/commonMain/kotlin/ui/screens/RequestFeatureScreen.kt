package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.screens.composables.GoBackTextTopAppBar
import ui.screens.composables.SimpleTextFAB
import ui.viewmodels.RequestFeatureViewModel

//----------------------------------------------------------------------------------------
@Composable
fun RequestFeatureScreen(
    onNav: () -> Unit,
    viewModel: RequestFeatureViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { RequestFeatureTopAppBar(
            onBack = onNav
        ) },
        floatingActionButton = {
            SimpleTextFAB(
                onClick = {},
                title = "Send"
            )
        }
    ) {
        Column {
            // FeatureRequest Fields
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun RequestFeatureTopAppBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    GoBackTextTopAppBar(
        title = "Request A Feature",
        onBackClick = onBack
    )
}