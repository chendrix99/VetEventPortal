package ui.screens.composables

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope
import ui.viewmodels.SavedSearchesViewModel

//----------------------------------------------------------------------------------------
@Composable
fun WarningDialog(
    viewModel: SavedSearchesViewModel,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog (
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(12.dp)
            ) {
                Text(text = message)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(12.dp).fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {
                            viewModel.viewModelScope.launch {
                                viewModel.deleteAllSavedResults()
                                onDismiss()
                            }
                        }
                    ) {
                        Text(text = "Yes")
                    }
                    OutlinedButton(
                        onClick = onDismiss
                    ) {
                        Text(text = "No")
                    }
                }
            }
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun EmptyResultsDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(12.dp)
            ) {
                Text(text = "No results found for your search")
                OutlinedButton(
                    onClick = onDismiss
                ) {
                    Text(text = "Okay")
                }
            }
        }
    }
}

//----------------------------------------------------------------------------------------
internal const val disclaimerAndAttribution = "Vet Event Portal and its creators present data obtained from the openFDA database of reported adverse events associated " +
        "with animal drugs and devices. This data is available for informational purposes only. Do not rely on information obtained from openFDA " +
        "to make decisions regarding medical care. Always speak to a veterinary professional regarding care for your animal and any questions or " +
        "concerns surrounding a drug or device.\n" +
        "\n" +
        "Vet Event Portal and its creators are not responsible for any clinical decisions made based on the information obtained from this application or their associated costs, " +
        "nor do we guarantee the accuracy or completeness of the data obtained from openFDA.\n" +
        "\n" +
        "By clicking on the below button labeled \"Accept\" you acknowledge this disclaimer and accept the limitation of liability it describes."

@Composable
fun DisclaimerDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(12.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        state = scrollState,
                        flingBehavior = ScrollableDefaults.flingBehavior()
                    )
            ) {
                Text(text = disclaimerAndAttribution, overflow = TextOverflow.Ellipsis)
                OutlinedButton(
                    onClick = onDismiss
                ) {
                    Text(text = "Accept")
                }
            }
        }
    }
}