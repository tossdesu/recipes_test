package com.tossdesu.recipestest.domain

/**
 * Sealed class for observe state of api response
 * and react in the UI.
 */
sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class GenericError(val message: String) : Resource<Nothing>()
    data class NetworkError(val isCacheExists: Boolean) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}