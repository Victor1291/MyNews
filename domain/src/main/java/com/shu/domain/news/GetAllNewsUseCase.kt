package com.shu.domain.news

import com.shu.entity.Photo
import kotlinx.coroutines.flow.Flow

class GetAllNewsUseCase(
    private val newsListRepository: NewsListRepository
) {

    suspend fun execute (countryCode: String, pageNumber: Int, category: String) =
        newsListRepository.getNews(countryCode = countryCode, pageNumber = pageNumber, category = category)


}