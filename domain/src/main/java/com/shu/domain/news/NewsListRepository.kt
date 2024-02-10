package com.shu.domain.news

import com.shu.entity.modelNews.INewsResponse

interface NewsListRepository {

    suspend fun getNews(countryCode: String, pageNumber: Int,category: String) : INewsResponse

    suspend fun getSearchNews(query: String, pageNumber: Int) : INewsResponse
}