package com.shu.data.api

import com.shu.data.api.models.NewsListDto
import com.shu.data.api.models.NewsResponseDto
import com.shu.indianews.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("news")
    suspend fun loadNews(
        @Query("category") category: String,
    ): NewsListDto

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponseDto

    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") countryCode: String = "us",
        @Query("category") category: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponseDto

}

/*
 https://inshorts.deta.dev/news?category=science
 */