package com.tossdesu.recipestest.data

import android.util.Log
import com.tossdesu.recipestest.data.network.ApiService
import com.tossdesu.recipestest.data.network.SafeApiCall
import com.tossdesu.recipestest.domain.RecipesRepository
import com.tossdesu.recipestest.domain.Resource
import com.tossdesu.recipestest.domain.entity.Recipe
import javax.inject.Inject

/**
 * Implementing the repository interface
 */
class RecipesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val safeApiCall: SafeApiCall
) : RecipesRepository {

    override suspend fun getRecipesUseCase(): Resource<List<Recipe>> = safeApiCall.run {
        Log.d("SUCCESS", "getRecipesUseCase")
        apiService.getRecipes().map { it.toRecipe() }
    }
}