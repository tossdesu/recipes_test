package com.tossdesu.recipestest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tossdesu.recipestest.domain.GetRecipesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<MainUiState>()
    val uiState: LiveData<MainUiState>
        get() = _uiState

    init {
        _uiState.value = MainUiState.Loading
    }

    fun getRecipes() {
        viewModelScope.launch {
            val recipes = getRecipesUseCase()
            _uiState.value = MainUiState.Success(recipes)
        }
    }
}