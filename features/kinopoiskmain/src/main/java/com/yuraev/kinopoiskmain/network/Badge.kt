package com.yuraev.kinopoiskmain.network

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun ListMovieTopBar(
    isOnly18: Boolean,
    onYearChange: (Boolean) -> Unit,
    isImdbMore7: Boolean,
    onImdbChange: (Boolean)-> Unit,
    isYear2024: Boolean,
    changeIs18Old: (Boolean) -> Unit,

    applyFilter: () -> Unit,
    isShowBadge: Boolean,


    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Movies") },
        actions = {
            BadgeIcon(isShowBadge) { expanded = !expanded }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Column {
                    Filter("Только 2024", isYear2024, onYearChange)
                    Filter("Imdb выше 7", isImdbMore7, onImdbChange)
                    Filter("Только 18+", isOnly18, changeIs18Old)
                    Button(
                        onClick = {
                            expanded = false
                            applyFilter()
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Filter")
                    }
                }

            }
        },
        modifier = modifier
    )
}

@Composable
fun Filter(
    text: String,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
        Text(text,modifier = Modifier.fillMaxWidth(0.8f))
        Checkbox(
            checked = enabled,
            onCheckedChange = onCheckedChange,
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeIcon(

    isShowBadge: Boolean,
    onExpanded: () -> Unit,
) {
    IconButton(onExpanded, modifier = Modifier.clip(RoundedCornerShape(15.dp))) {
        BadgedBox(
            badge = {
                if (isShowBadge) {
                    Badge(
                        modifier = Modifier
                            .size(12.dp)
                    )
                }

            },

            ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Filter",
            )
        }
    }


}

