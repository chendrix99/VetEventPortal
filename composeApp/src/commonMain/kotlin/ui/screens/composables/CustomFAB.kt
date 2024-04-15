package ui.screens.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//----------------------------------------------------------------------------------------
@Composable
fun SimpleTextFAB(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        backgroundColor = MaterialTheme.colors.secondaryVariant
    ) {
        Text(
            text = title
        )
    }
}