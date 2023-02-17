package com.tossdesu.recipestest.domain

import com.tossdesu.recipestest.domain.entity.Recipe

interface RecipesRepository {

    /**
     * Execute recipes downloading request
     * @return list of Recipe objects
     */
    fun getRecipesUseCase(): List<Recipe>
}