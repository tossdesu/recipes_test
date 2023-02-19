package com.tossdesu.recipestest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tossdesu.recipestest.data.database.entity.RecipeDbEntity.Companion.NAME
import com.tossdesu.recipestest.domain.entity.Recipe

/**
 * Recipe DB Entity
 *
 * JSON example:
 * ```
 * {
 *   "id": "",
 *   "name": "",
 *   "headline": "",
 *   "description": "",
 *   "country": "",
 *   "difficulty": 0,
 *   "time": 0,
 *   "calories": 0,
 *   "carbos": 0,
 *   "fats": 0,
 *   "proteins": 0,
 *   "image": "",
 *   "thumb": "",
 * }
 * ```
 */
@Entity(
    tableName = NAME
)
data class RecipeDbEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val headline: String,
    val description: String,
    val country: String,
    val difficulty: Int,
    val time: Int,
    val calories: Int,
    val carbos: Int,
    val fats: Int,
    val proteins: Int,
    val image: String
) {

    /**
     * Convert this entity into in-app [Recipe] instance.
     */
    fun toRecipe() = Recipe(
        id = id,
        name = name,
        headline = headline,
        description = description,
        country = country,
        difficulty = difficulty,
        time = time,
        calories = calories,
        carbos = carbos,
        fats = fats,
        proteins = proteins,
        image = image
    )


    companion object {

        const val NAME = "recipe"

        /**
         * Convert this entity into DB [RecipeDbEntity] instance.
         */
        fun fromRecipe(recipe: Recipe) = RecipeDbEntity(
            id = recipe.id,
            name = recipe.name,
            headline = recipe.headline,
            description = recipe.description,
            country = recipe.country,
            difficulty = recipe.difficulty,
            time = recipe.time,
            calories = recipe.calories,
            carbos = recipe.carbos,
            fats = recipe.fats,
            proteins = recipe.proteins,
            image = recipe.image
        )
    }
}