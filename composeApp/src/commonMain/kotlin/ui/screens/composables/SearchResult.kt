package ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.SearchResultData

@Composable
fun SimpleSearchResult(
    data: SearchResultData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(6.dp)
    ) {
        Text(text = "Animal Species: " + data.animal?.species.toString())
        if (data.drug != null) {
            for (drug in data.drug) {
                drug.active_ingredients?.forEach {
                    Text(text = it.name.toString())
                }
            }
        }
    }
}

@Composable
fun SimpleSearchResultCard(
    data: SearchResultData,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SimpleSearchResult(data)
    }
}

@Composable
fun SearchResultAndSaveButton(
    data: SearchResultData,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleSearchResult(data)
        OutlinedButton(
            onClick = onSave,
            shape = CircleShape,
            modifier = modifier
                .padding(6.dp)
        ) {
            Icon(Icons.Filled.Star, contentDescription = "Save Search")
        }
    }
}

@Composable
fun SearchResultAndSaveButtonCard(
    data: SearchResultData,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SearchResultAndSaveButton(data, onSave)
    }
}