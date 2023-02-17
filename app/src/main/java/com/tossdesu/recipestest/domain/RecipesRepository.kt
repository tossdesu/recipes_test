package com.tossdesu.recipestest.domain

import com.tossdesu.recipestest.domain.entity.Recipe

interface RecipesRepository {

    /**
     * Execute all recipes downloading request
     * @return list of recipes
     */
    fun getRecipesUseCase(): List<Recipe>
}