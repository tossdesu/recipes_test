package com.tossdesu.recipestest.domain.usacase

import com.tossdesu.recipestest.domain.RecipesRepository
import com.tossdesu.recipestest.domain.entity.Recipe
import javax.inject.Inject

class GetRecipesFromDbUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(): List<Recipe> {
        return repository.getRecipesFromDbUseCase()
    }
}