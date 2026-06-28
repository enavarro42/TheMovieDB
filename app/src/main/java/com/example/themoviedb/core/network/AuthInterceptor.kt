package com.example.themoviedb.core.network

import com.example.themoviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", BuildConfig.TMDB_ACCESS_TOKEN)
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}