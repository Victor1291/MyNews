package com.shu.data.api.models

import com.shu.entity.modelNews.IDetailNews

data class DetailNewsDto(
   override val author: String?,
   override val image: String? ,
   override val title: String? ,
   override val description: String?,
   override val url: String?,
   override val content: String?
): IDetailNews
