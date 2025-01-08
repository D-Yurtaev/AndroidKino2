package com.yuraev.kinopoiskmain.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuraev.kinopoiskmain.Docs
import com.yuraev.kinopoiskmain.DocsUI

@Composable
fun FavoriteKinopoisk(
    goToDetail: (DocsUI) -> Unit,
    modifier: Modifier = Modifier) {
    FavoriteKinopoisk(goToDetail,modifier = modifier, viewModel = hiltViewModel())

}

@Composable
internal fun FavoriteKinopoisk(
    goToDetail: (DocsUI) -> Unit ,
    viewModel: FavoriteViewModel,
    modifier: Modifier = Modifier) {
    val favorite by viewModel.favorite.collectAsState()
    Docs(docs = favorite,goToDetail = goToDetail, modifier = modifier)
}
