package com.shu.data.api.models

import com.shu.entity.modelNews.IArticle

data class ArticleDto(
   override val id: Int = 0,
   override val author: String?,
   override val content: String?,
   override val description: String?,
   override val publishedAt: String?,
   override val title: String?,
   override val url: String?,
   override val urlToImage: String?
): IArticle