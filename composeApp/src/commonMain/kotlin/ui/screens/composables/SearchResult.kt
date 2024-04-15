package ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.SearchResultData

//----------------------------------------------------------------------------------------
@Composable
fun SimpleSearchResult(
    data: SearchResultData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(6.dp)
    ) {
        Text(text = "Animal Species:", fontWeight = FontWeight.Bold)
        Text(text = data.animal?.species ?: "Unknown")

        Text(text = "Animal Age:", fontWeight = FontWeight.Bold)
        Text(text = (data.animal?.age?.min ?: "Unknown age") + " " + (data.animal?.age?.unit ?: "Unknown length"))

        Text(text = "Animal Breed:", fontWeight = FontWeight.Bold)
        Text(text = if (data.animal?.breed?.breed_component.toString() == "null") "Unknown"
            else data.animal?.breed?.breed_component.toString().replace("\"", "").replace("\\", ""))

        if (!data.drug.isNullOrEmpty()) {
            val drug = data.drug[0]
            Text(text = "Drug Active Ingredients:", fontWeight = FontWeight.Bold)
            drug.active_ingredients?.forEach {
                if (it.name != null) {
                    Text(text = it.name)
                }
            }

            Text(text = "Adverse Event:", fontWeight = FontWeight.Bold)
            Text(text = data.serious_ae ?: "Unknown")
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun SavedSearchResultCard(
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
        SearchResultAndSaveButton(data, { }, false, { }, false)
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun SearchResultAndSaveButton(
    data: SearchResultData,
    onSave: () -> Unit,
    showSave: Boolean,
    onMore: () -> Unit,
    showMore: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleSearchResult(
            data,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showSave) {
                OutlinedButton(
                    onClick = onSave,
                    shape = CircleShape,
                    modifier = modifier.padding(6.dp)
                ) {
                    Icon(
                        Icons.Filled.Star, contentDescription = "Save Search",
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
            if (showMore) {
                OutlinedButton(
                    onClick = onMore,
                    shape = CircleShape,
                    modifier = Modifier.padding(6.dp)
                ) {
                    Icon(
                        Icons.Filled.MoreVert, contentDescription = "More Info",
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        }
    }
}

//----------------------------------------------------------------------------------------
@Composable
fun SearchResultAndSaveButtonCard(
    data: SearchResultData,
    onSave: () -> Unit,
    onMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SearchResultAndSaveButton(data, onSave, true, onMore, true)
    }
}