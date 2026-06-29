package com.example.themoviedb.ui.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.themoviedb.domain.model.MediaType
import com.example.themoviedb.ui.component.FeaturedMovieBanner

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // Banner con película destacada (primera de Now Playing)
        item {
            uiState.nowPlayingMovies.firstOrNull()?.let { featured ->
                FeaturedMovieBanner(
                    movie = featured,
                    onClick = {  }
                )
            }
        }
    }
}