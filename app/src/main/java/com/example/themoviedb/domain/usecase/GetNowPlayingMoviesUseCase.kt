package com.example.themoviedb.domain.usecase

import com.example.themoviedb.domain.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val repo: MovieRepository) {
    suspend operator fun invoke(page: Int = 1) = repo.getNowPlaying(page)
}