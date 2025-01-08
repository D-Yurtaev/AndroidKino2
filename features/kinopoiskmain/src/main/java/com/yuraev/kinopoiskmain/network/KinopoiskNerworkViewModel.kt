package com.yuraev.kinopoiskmain.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuraev.kinopoiskdata.MoviePreferencesRepository
import com.yuraev.kinopoiskdata.RequestResult
import com.yuraev.kinopoiskmain.DocsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.List

@HiltViewModel
class KinopoiskNerworkViewModel @Inject constructor(
    private val moviePreferencesRepository: MoviePreferencesRepository,
    private val getFromServerSUseCase: GetFromServerSUseCase,
    private val badge: Badge
) : ViewModel() {

    private val _isOnly18 = MutableStateFlow(false)
    val isOnly18 = _isOnly18.asStateFlow()
    private val _isImdbMore7 = MutableStateFlow(false)
    val isImdbMore7 = _isImdbMore7.asStateFlow()
    private val _isYear2024 = MutableStateFlow(false)
    val isYear2024 = _isYear2024.asStateFlow()
    private suspend fun getImdbFromPreferences(): Boolean {
        return moviePreferencesRepository.isImdbMoreSeven.first()
    }

    private suspend fun getYearFromPreferences(): Boolean {
        return moviePreferencesRepository.isOnly2024.first()
    }

    private suspend fun getIs18FromPreferences(): Boolean {
        return moviePreferencesRepository.is18Old.first()
    }

    init {
        viewModelScope.launch {
            _isOnly18.value = getIs18FromPreferences()
            _isImdbMore7.value = getImdbFromPreferences()
            _isYear2024.value = getYearFromPreferences()
        }
    }

    private val _isShowBadge = MutableStateFlow(badge)
    val isShowBadge = _isShowBadge.asStateFlow()


    private val _docsState = MutableStateFlow(getDocs())

    @OptIn(ExperimentalCoroutinesApi::class)
    val docsState = _docsState.flatMapLatest { it }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = DocsState.Loading()
    )

    fun onImdbChange(value: Boolean) {
        _isImdbMore7.value = value
    }

    fun onYearChange(value: Boolean) {
        _isYear2024.value = value
    }

    fun onIs18Change(value: Boolean) {
        _isOnly18.value = value
    }

    fun applyFilter() {
        viewModelScope.launch {
            moviePreferencesRepository.saveMovieYear(isOnly2024 = isYear2024.value)
            moviePreferencesRepository.saveImdb(isImdbMoreSeven = isImdbMore7.value)
            moviePreferencesRepository.is18Old(is18Old = isOnly18.value)
        }
        _docsState.value = getDocs()
        _isShowBadge.value.apply { this.show() }

    }


    fun getDocs(): Flow<DocsState> {
        val ageRating = if (isOnly18.value) "18" else "0-17"
        val imdbRating = if (isImdbMore7.value) "7-10" else "0-10"
        val year = if (isYear2024.value) "2024" else "1900-2024"
        return getFromServerSUseCase.invoke(
            ageRating = ageRating,
            imdbRating = imdbRating,
            year = year
        ).map { it.toDocsState() }

    }

}


internal fun RequestResult<List<DocsUI>>.toDocsState(): DocsState {
    return when (this) {
        is RequestResult.Error -> DocsState.Error(data)
        is RequestResult.InProgress -> DocsState.Loading(data)
        is RequestResult.Success -> DocsState.Success(data)
    }
}

sealed class DocsState {

    data class Loading(val docs: List<DocsUI>? = null) : DocsState()
    data class Success(val docs: List<DocsUI>) : DocsState()
    data class Error(val docs: List<DocsUI>? = null) : DocsState()
}

class Badge @Inject constructor(
) {
    var isShow = false
        private set

    fun show() {
        isShow = true
    }
}
