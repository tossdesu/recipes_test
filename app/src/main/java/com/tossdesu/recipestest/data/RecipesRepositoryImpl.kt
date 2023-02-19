package com.tossdesu.recipestest.data

import com.tossdesu.recipestest.data.database.RecipesDao
import com.tossdesu.recipestest.data.database.entity.RecipeDbEntity
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
    private val recipesDao: RecipesDao,
    private val apiService: ApiService,
    private val safeApiCall: SafeApiCall
) : RecipesRepository {

    /**
     * Execute recipes downloading request
     * @return Success|GenericError|NetworkError object of [Resource] sealed class
     */
    override suspend fun getRecipesFromNetworkUseCase(): Resource<List<Recipe>> = safeApiCall.run {
        apiService.getRecipes().map { it.toRecipe() }
    }

    /**
     * Put list of [Recipe] objects into DB
     */
    override suspend fun saveRecipesUseCase(recipes: List<Recipe>) {
        val recipesDbList = recipes.map { RecipeDbEntity.fromRecipe(it) }
        recipesDao.saveRecipes(recipesDbList)
    }

    /**
     * Delete all recipes from DB
     */
    override suspend fun deleteRecipesUseCase() {
        recipesDao.deleteRecipes()
    }

    /**
     * Get all recipes from DB
     * @return list of [Recipe] objects
     */
    override suspend fun getRecipesFromDbUseCase(): List<Recipe> {
        val recipesDbList = recipesDao.getRecipes()
        return recipesDbList.map { it.toRecipe() }
    }
}