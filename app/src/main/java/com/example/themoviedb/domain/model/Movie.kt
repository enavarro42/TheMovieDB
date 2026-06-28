package com.example.themoviedb.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,   // Ruta relativa: "/abc123.jpg"
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val genreIds: List<Int>
) {
    // URL completa del poster
    fun posterUrl(size: String = "w500"): String? =
        posterPath?.let { "https://image.tmdb.org/t/p/$size$it" }

    fun backdropUrl(size: String = "w780"): String? =
        backdropPath?.let { "https://image.tmdb.org/t/p/$size$it" }

    val ratingFormatted: String get() = String.format("%.1f", voteAverage)
    val year: String get() = releaseDate?.take(4) ?: "—"
}
