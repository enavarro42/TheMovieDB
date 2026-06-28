package com.example.themoviedb.data.repository

import com.example.themoviedb.core.network.ApiResult
import com.example.themoviedb.core.network.safeApiCall
import com.example.themoviedb.core.utils.CoroutineDispatchers
import com.example.themoviedb.data.remote.api.MovieApi
import com.example.themoviedb.data.remote.dto.toDomain
import com.example.themoviedb.domain.model.Movie
import com.example.themoviedb.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dispatchers: CoroutineDispatchers
): MovieRepository {
    override suspend fun getNowPlaying(page: Int): ApiResult<List<Movie>> =
        withContext(dispatchers.io) {
            safeApiCall { api.getNowPlaying(page = page).results.map { it.toDomain() } }
        }
}