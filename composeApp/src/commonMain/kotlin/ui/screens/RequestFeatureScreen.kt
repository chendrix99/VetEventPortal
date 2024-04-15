package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.screens.composables.GoBackTextTopAppBar


//----------------------------------------------------------------------------------------
@Composable
fun RequestFeatureScreen(
    onNav: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { RequestFeatureTopAppBar(
            onBack = onNav
        ) }
    ) {
        Column {
            Text(
                text = "Please send a brief description of the feature that you would like to this email: calebhendrix23@icloud.com",
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.padding(12.dp)
            )
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