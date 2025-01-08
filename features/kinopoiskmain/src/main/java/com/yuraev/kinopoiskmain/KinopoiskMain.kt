package com.yuraev.kinopoiskmain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yuraev.kinopoiskmain.network.DocsState
import com.yuraev.kinopoiskmain.network.KinopoiskNerworkViewModel
import com.yuraev.kinopoiskmain.network.ListMovieTopBar

@Composable
fun NetworkDocs(
    goToDetail: (DocsUI) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: KinopoiskNerworkViewModel
) {
    val docsState by viewModel.docsState.collectAsState()
    val isOnly18 by viewModel.isOnly18.collectAsState()
    val isImdbMore7 by viewModel.isImdbMore7.collectAsState()
    val isYear2024 by viewModel.isYear2024.collectAsState()
    val isShowBadge by viewModel.isShowBadge.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        ListMovieTopBar(
            isOnly18 = isOnly18,
            isImdbMore7 = isImdbMore7,
            isYear2024 = isYear2024,
            onYearChange = viewModel::onYearChange,
            onImdbChange = viewModel::onImdbChange,
            changeIs18Old = viewModel::onIs18Change,
            applyFilter = viewModel::applyFilter,
            isShowBadge = isShowBadge.isShow,
            modifier = Modifier

        )
        when (docsState) {
            is DocsState.Error -> ErrorDoc(modifier)
            is DocsState.Loading -> LoadingDoc(modifier)
            is DocsState.Success -> Docs((docsState as DocsState.Success).docs, goToDetail)
        }
    }



}

@Composable
internal fun ErrorDoc(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error")
    }
}

@Composable
internal fun LoadingDoc(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
internal fun Docs(
    docs: List<DocsUI>,
    goToDetail: (DocsUI) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(docs, key = { it.id }) { doc ->
            PreviewDoc(doc, goToDetail)
        }
    }

}


@Composable
fun PreviewDoc(

    doc: DocsUI,
    goToDetail: (DocsUI) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { goToDetail(doc) },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(doc.name ?: "", style = MaterialTheme.typography.headlineMedium, maxLines = 1)
            Spacer(modifier = Modifier.size(4.dp))
            AsyncImage(
                doc.previewUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(150.dp)
                    .widthIn(150.dp)
            )
            Text(
                doc.shortDescription ?: "",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )
        }
    }

}

