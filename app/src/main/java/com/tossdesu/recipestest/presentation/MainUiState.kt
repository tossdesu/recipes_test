package com.tossdesu.recipestest.presentation

import com.tossdesu.recipestest.domain.entity.Recipe

sealed class MainUiState {
    object Loading : MainUiState()
    class Success(val recipes: List<Recipe>) : MainUiState()
    class Error(val error: Throwable) : MainUiState()
}