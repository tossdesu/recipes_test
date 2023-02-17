package com.tossdesu.recipestest.domain

import com.tossdesu.recipestest.domain.entity.Recipe

class GetRecipesUseCase(
    private val repository: RecipesRepository
) {

    operator fun invoke(): List<Recipe> {
        return repository.getRecipesUseCase()
    }
}