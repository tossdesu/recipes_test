package com.tossdesu.recipestest.domain

import com.tossdesu.recipestest.domain.entity.Recipe

interface RecipesRepository {

    /**
     * Execute recipes downloading request
     * @return Success|GenericError|NetworkError object of [Resource] sealed class
     */
    suspend fun getRecipesFromNetworkUseCase(): Resource<List<Recipe>>

    /**
     * Put list of [Recipe] objects into DB
     */
    suspend fun saveRecipesUseCase(recipes: List<Recipe>)

    /**
     * Delete all recipes from DB
     */
    suspend fun deleteRecipesUseCase()

    /**
     * Get all recipes from DB
     * @return list of [Recipe] objects
     */
    suspend fun getRecipesFromDbUseCase(): List<Recipe>
}