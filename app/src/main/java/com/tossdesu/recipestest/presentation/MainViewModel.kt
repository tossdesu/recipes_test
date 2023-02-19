package com.tossdesu.recipestest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tossdesu.recipestest.domain.Resource
import com.tossdesu.recipestest.domain.entity.Recipe
import com.tossdesu.recipestest.domain.usacase.DeleteRecipesUseCase
import com.tossdesu.recipestest.domain.usacase.GetRecipesFromDbUseCase
import com.tossdesu.recipestest.domain.usacase.GetRecipesFromNetworkUseCase
import com.tossdesu.recipestest.domain.usacase.SaveRecipesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRecipesFromNetworkUseCase: GetRecipesFromNetworkUseCase,
    private val saveRecipesUseCase: SaveRecipesUseCase,
    private val deleteRecipesUseCase: DeleteRecipesUseCase,
    private val getRecipesFromDbUseCase: GetRecipesFromDbUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<Resource<List<Recipe>>>()
    val uiState: LiveData<Resource<List<Recipe>>>
        get() = _uiState

    init {
        getRecipes()
    }

    fun getRecipes() {
        // show progress bar while loading recipes
        _uiState.value = Resource.Loading
        viewModelScope.launch {
            // loading recipes by network
            val response = getRecipesFromNetworkUseCase()
            if (response is Resource.Success) {
                // if success, put recipes into DB for cache
                cacheRecipesInDb(response.data)
            } else if(response is Resource.NetworkError) {
                // if NetworkError, get recipes from DB
                val recipesFromDb = getRecipesFromDbUseCase()
                if (recipesFromDb.isNotEmpty()) {
                    // if recipes exists in DB, let UI know
                    _uiState.value = Resource.Success(recipesFromDb)
                    // and show Network Error
                    _uiState.value = response.copy(isCacheExists = true)
                    return@launch
                }
            }
            _uiState.value = response
        }
    }

    private suspend fun cacheRecipesInDb(recipes: List<Recipe>) {
            deleteRecipesUseCase()
            saveRecipesUseCase(recipes)
    }
}