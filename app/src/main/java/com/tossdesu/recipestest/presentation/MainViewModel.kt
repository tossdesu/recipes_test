package com.tossdesu.recipestest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tossdesu.recipestest.domain.GetRecipesUseCase
import com.tossdesu.recipestest.domain.Resource
import com.tossdesu.recipestest.domain.entity.Recipe
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<Resource<List<Recipe>>>()
    val uiState: LiveData<Resource<List<Recipe>>>
        get() = _uiState

    init {
        getRecipes()
    }

    fun getRecipes() {
        _uiState.value = Resource.Loading
        viewModelScope.launch {
            _uiState.value = getRecipesUseCase()
        }
    }
}