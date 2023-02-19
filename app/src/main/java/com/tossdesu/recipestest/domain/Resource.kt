package com.tossdesu.recipestest.domain

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class GenericError(val message: String) : Resource<Nothing>()
    object NetworkError : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}