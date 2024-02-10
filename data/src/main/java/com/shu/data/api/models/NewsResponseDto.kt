package com.shu.data.api.models

import com.shu.entity.modelNews.INewsResponse


data class NewsResponseDto(
   override val articles: List<ArticleDto>,
   override val status: String,
   override val totalResults: Int
) : INewsResponse