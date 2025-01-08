package com.yuraev.kinopoiskmain.fulldoc

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.yuraev.kinopoiskmain.DocsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullDocViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val isExistInFavoritesUseCase: IsExistInFavoritesUseCase,
    private val saveToFavoriteUseCase: SaveToFavoriteUseCase
) : ViewModel() {

    val doc: DocsUI = savedStateHandle.toRoute<DocsUI>()
    fun addToFavorite(doc: DocsUI) {
        viewModelScope.launch {
            saveToFavoriteUseCase(doc)
        }
    }
    val isExist = isExistInFavoritesUseCase(doc.id)
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)
}
