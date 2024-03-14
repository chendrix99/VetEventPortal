package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.screens.composables.GoBackTextTopAppBar
import ui.viewmodels.SpecialReportsViewModel

//----------------------------------------------------------------------------------------
@Composable
fun SpecialReportsScreen(
    onNav: () -> Unit,
    viewModel: SpecialReportsViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SpecialReportTopAppBar(
            onBack = onNav
        ) }
    ) {
        Column {
            // The special FDA reports
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun SpecialReportTopAppBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    GoBackTextTopAppBar(
        title = "Special Reports",
        onBackClick = onBack
    )
}