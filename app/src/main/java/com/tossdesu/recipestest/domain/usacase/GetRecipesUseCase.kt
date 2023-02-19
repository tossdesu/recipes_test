package com.tossdesu.recipestest.domain.usacase

import com.tossdesu.recipestest.domain.RecipesRepository
import com.tossdesu.recipestest.domain.Resource
import com.tossdesu.recipestest.domain.entity.Recipe
import javax.inject.Inject

class GetRecipesFromNetworkUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(): Resource<List<Recipe>> {
        return repository.getRecipesFromNetworkUseCase()
    }
}