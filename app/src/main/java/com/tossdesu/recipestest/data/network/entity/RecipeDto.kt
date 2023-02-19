package com.tossdesu.recipestest.data.network.entity

import com.tossdesu.recipestest.domain.entity.Recipe

/**
 * Response body for `GET /android-test/recipes.json` HTTP-request.
 *
 * JSON example:
 * ```
 * {
 *   "id": "",
 *   "name": "",
 *   "headline": "",
 *   "description": "",
 *   "country": "" or Null,
 *   "difficulty": 0,
 *   "time": "",
 *   "calories": "",
 *   "carbos": "",
 *   "fats": "",
 *   "proteins": "",
 *   "image": "",
 *   "thumb": "",
 * }
 * ```
 */
data class RecipeDto(
    val id: String,
    val name: String,
    val headline: String,
    val description: String,
    val country: String? = null,
    val difficulty: Int,
    val time: String,
    val calories: String,
    val carbos: String,
    val fats: String,
    val proteins: String,
    val image: String,
    val thumb: String
) {

    /**
     * Convert this entity into in-app [Recipe] instance.
     */
    fun toRecipe(): Recipe = Recipe(
        id = id,
        name = name,
        headline = headline,
        description = description,
        country = country ?: "",
        difficulty = difficulty,
        time = getTime(time),
        calories = getInt(calories),
        carbos = getInt(carbos),
        fats = getInt(fats),
        proteins = getInt(proteins),
        image = image
    )

    private fun getInt(str: String): Int {
        val subStr = str.split(" ")[0]
        return try { subStr.toInt() } catch (e: NumberFormatException) { 0 }
    }

    private fun getTime(str: String): Int {
        val subStr = str.substring(2, str.length - 1)
        return try { subStr.toInt() } catch (e: NumberFormatException) { 0 }
    }
}
