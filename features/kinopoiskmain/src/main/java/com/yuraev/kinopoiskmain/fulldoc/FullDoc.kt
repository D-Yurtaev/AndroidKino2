package com.yuraev.kinopoiskmain.fulldoc

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yuraev.kinopoiskmain.DocsUI
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FullDoc(
    doc: DocsUI,
    viewModel: FullDocViewModel = hiltViewModel(),
    modifier: Modifier = Modifier.Companion) {
    val isExist by viewModel.isExist.collectAsState()
    Column(modifier = modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally){
        Text(doc.name ?: "", style = MaterialTheme.typography.headlineMedium, maxLines = 1)
        Log.d("TAGURL", "FullDoc: ${doc.url}")
        AsyncImage(
            doc.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .heightIn(150.dp)
                .widthIn(150.dp)
        )
        Text(doc.description ?: "", style = MaterialTheme.typography.bodyMedium)
        Text("Год: ${doc.year?:""}", style = MaterialTheme.typography.bodyMedium)
        Text("Рейтинг: ${doc.imdbRating?:""}", style = MaterialTheme.typography.bodyMedium)
        Text("Возраст: ${doc.ageRating?:""}", style = MaterialTheme.typography.bodyMedium)
        if (!isExist) {
            Button(onClick = { viewModel.addToFavorite(doc) }) {
                Text(text = "Добавить в избранное")
            }
        }



    }

}
