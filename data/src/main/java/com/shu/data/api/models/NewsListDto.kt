package com.shu.data.api.models

import com.google.gson.annotations.SerializedName
import com.shu.entity.modelNews.INewsList


data class NewsListDto (

    @SerializedName("category" ) override var category : String?         = null,
    @SerializedName("data"     ) override var data     : List<NewsDataDto>,
    @SerializedName("success"  ) override var success  : Boolean?        = null

): INewsList
