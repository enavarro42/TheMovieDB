package com.example.themoviedb.domain.repository

import com.example.themoviedb.core.network.ApiResult
import com.example.themoviedb.domain.model.Movie

interface MovieRepository {
    suspend fun getNowPlaying(page: Int = 1): ApiResult<List<Movie>>
}