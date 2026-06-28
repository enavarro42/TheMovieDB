package com.example.themoviedb.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.core.network.ApiResult
import com.example.themoviedb.core.network.toUserMessage
import com.example.themoviedb.domain.model.Movie
import com.example.themoviedb.domain.usecase.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNowPlaying: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init { loadHome() }

    fun loadHome() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val nowPlayingDeferred = async { getNowPlaying() }

            val nowPlaying = nowPlayingDeferred.await()

            _uiState.update {
                it.copy(
                    isLoading = false,
                    nowPlayingMovies = (nowPlaying as? ApiResult.Success)?.data ?: it.nowPlayingMovies,
                    errorMessage = listOf(nowPlaying)
                        .filterIsInstance<ApiResult.Error>()
                        .firstOrNull()
                        ?.exception?.toUserMessage()
                )
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)