package com.tossdesu.recipestest.data.network

import com.tossdesu.recipestest.data.network.entity.RecipeDto
import retrofit2.http.GET

interface ApiService {

    @GET("android-test/recipes.json")
    suspend fun getRecipes(): List<RecipeDto>
}