package com.yuraev.kinopoiskmain.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getFavoriteUseCase: GetFavoriteUseCase,
): ViewModel() {
    val favorite = getFavoriteUseCase.invoke().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList(),
    )

}
