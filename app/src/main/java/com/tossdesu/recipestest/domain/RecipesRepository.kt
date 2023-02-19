package com.tossdesu.recipestest.domain

import com.tossdesu.recipestest.domain.entity.Recipe

interface RecipesRepository {

    /**
     * Execute recipes downloading request
     * @return Object of Resource class with list of Recipe or Exception
     */
    suspend fun getRecipesUseCase(): Resource<List<Recipe>>
}