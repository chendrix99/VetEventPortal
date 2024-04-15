package ui.screens.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//----------------------------------------------------------------------------------------
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextChip(
    text: String,
    checked: Boolean,
    onClicked: () -> Unit
) {
    Chip(
        onClick = onClicked,
        leadingIcon = { if (checked) {
            Icon(Icons.Filled.Check, contentDescription = null)
        } },
        colors = when (checked) {
            true -> ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.secondaryVariant, contentColor = MaterialTheme.colors.onSecondary)
            false -> ChipDefaults.chipColors()
        },
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Text(text)
    }
}