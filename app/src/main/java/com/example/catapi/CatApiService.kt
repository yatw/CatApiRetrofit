package com.example.catapi

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface CatApiService {


    @GET("search/")
    suspend fun getCats(
        @Query("size") size: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String,
        @Query("order") order: String
    ): List<CatApiResponse>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/images/"

        fun create(): CatApiService {
            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS) // this line fix the problem
                 //add header to every request
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("x-api-key", "f81b24bd-e6a1-417a-9b5e-da7f3cbc5c4d")
                        .build()
                    return@Interceptor chain.proceed(request)
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatApiService::class.java)
        }
    }
}