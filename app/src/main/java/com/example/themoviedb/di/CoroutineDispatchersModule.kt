package com.example.themoviedb.di

import com.example.themoviedb.core.utils.CoroutineDispatchers
import com.example.themoviedb.core.utils.DefaultCoroutineDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineDispatchersModule {
    @Binds
    @Singleton
    abstract fun bindDispatchers(impl: DefaultCoroutineDispatchers): CoroutineDispatchers
}