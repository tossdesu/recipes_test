package com.tossdesu.recipestest.di

import com.tossdesu.recipestest.data.RecipesRepositoryImpl
import com.tossdesu.recipestest.data.network.ApiFactory
import com.tossdesu.recipestest.data.network.ApiService
import com.tossdesu.recipestest.domain.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRecipesRepositoryImpl(impl: RecipesRepositoryImpl): RecipesRepository

    companion object {

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}