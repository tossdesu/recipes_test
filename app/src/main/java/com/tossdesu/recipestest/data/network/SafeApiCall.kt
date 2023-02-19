package com.tossdesu.recipestest.data.network

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.tossdesu.recipestest.domain.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Wrapping class for handling exceptions coming from
 * Retrofit and Moshi when execute api request.
 * @return
 */
class SafeApiCall @Inject constructor() {

    suspend fun <T> run(block: suspend () -> T): Resource<T> {
        // Returning api response wrapped in Resource class
        return try {
            // calling api lambda
            Resource.Success(block())
        } catch (e: Exception) {
            when(e) {
                // moshi
                is JsonDataException, is JsonEncodingException -> {

                    Resource.GenericError(
                        "При работе с полученными данными произошла ошибка.\n ${e.message}"
                    )
                }
                // retrofit
                is HttpException -> {
                    Resource.GenericError(
                        "HttpException: ${e.message()}"
                    )
                }
                // network connection
                is IOException -> {
                    Resource.NetworkError
                }
                // unknown error
                else -> {
                    Resource.GenericError(
                        "Что-то пошло не так.\n ${e.message}"
                    )
                }
            }
        }
    }
}