package com.tossdesu.recipestest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tossdesu.recipestest.data.database.entity.RecipeDbEntity

@Dao
interface RecipesDao {

    /**
     * Put list of [RecipeDbEntity] objects into DB
     */
    @Insert
    suspend fun saveRecipes(recipesList: List<RecipeDbEntity>)

    /**
     * Delete all recipes from DB
     */
    @Query("DELETE FROM ${RecipeDbEntity.NAME}")
    suspend fun deleteRecipes()

    /**
     * Get all recipes from DB
     * @return list of [RecipeDbEntity] objects
     */
    @Query("SELECT * FROM ${RecipeDbEntity.NAME}")
    suspend fun getRecipes(): List<RecipeDbEntity>
}