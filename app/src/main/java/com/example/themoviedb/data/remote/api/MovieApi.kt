package com.example.themoviedb.data.remote.api

import com.example.themoviedb.data.remote.dto.MovieDto
import com.example.themoviedb.data.remote.dto.PagedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

const val LANGUAGE_TAG = "language"
const val LANGUAGE = "es-ES"
const val PAGE = "page"
const val CURRENT_PAGE = 1

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query(LANGUAGE_TAG) language: String = LANGUAGE,
        @Query(PAGE) page: Int = CURRENT_PAGE
    ): PagedResponseDto<MovieDto>
}