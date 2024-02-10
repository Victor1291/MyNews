package com.shu.data.api

import com.shu.domain.news.NewsListRepository
import javax.inject.Inject


class NewsListRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsListRepository {

    override suspend fun getNews(countryCode: String, pageNumber: Int, category: String) =
        newsApi.getHeadlines(countryCode = countryCode, page = pageNumber, category = category)

    override suspend fun getSearchNews(query: String, pageNumber: Int) =
        newsApi.getEverything(query = query, page = pageNumber)

}