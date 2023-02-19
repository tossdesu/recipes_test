package com.tossdesu.recipestest.domain.usacase

import com.tossdesu.recipestest.domain.RecipesRepository
import javax.inject.Inject

class DeleteRecipesUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke() {
        return repository.deleteRecipesUseCase()
    }
}