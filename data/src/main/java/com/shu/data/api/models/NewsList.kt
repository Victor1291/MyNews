package com.shu.data.api.models


data class NewsList (

    @SerializedName("category" ) var category : String?         = null,
    @SerializedName("data"     ) var data     : List<NewsData>,
    @SerializedName("success"  ) var success  : Boolean?        = null

)
