package com.shu.data.api.models

import com.google.gson.annotations.SerializedName
import com.shu.entity.modelNews.INewsData


data class NewsDataDto (

    @SerializedName("author"      ) override var author      : String? = null,
    @SerializedName("content"     ) override var content     : String? = null,
    @SerializedName("date"        ) override var date        : String? = null,
    @SerializedName("imageUrl"    ) override var imageUrl    : String? = null,
    @SerializedName("readMoreUrl" ) override var readMoreUrl : String? = null,
    @SerializedName("time"        ) override var time        : String? = null,
    @SerializedName("title"       ) override var title       : String? = null,
    @SerializedName("url"         ) override var url         : String? = null

) : INewsData
