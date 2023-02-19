package com.tossdesu.recipestest.domain

import com.tossdesu.recipestest.domain.entity.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(): Resource<List<Recipe>> {
        return repository.getRecipesUseCase()
    }
}