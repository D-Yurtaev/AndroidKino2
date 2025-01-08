package com.yuraev.kinopoiskmain.network

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuraev.kinopoiskmain.DocsUI
import com.yuraev.kinopoiskmain.NetworkDocs

@Composable
fun NetworkKinopoisk(
    goToDetail: (DocsUI) -> Unit,
    modifier: Modifier = Modifier) {

    NetworkDocs(goToDetail = goToDetail,modifier = modifier, viewModel = hiltViewModel())


}
