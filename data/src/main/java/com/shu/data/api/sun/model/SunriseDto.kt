package com.shu.data.api.sun.model

import com.google.gson.annotations.SerializedName
import com.shu.entity.icollection.Sunrise


data class SunriseDto (

    @SerializedName("results" ) override var results : ResultsDto,
    @SerializedName("status"  ) override var status  : String,
    @SerializedName("tzId"    ) override var tzId    : String

): Sunrise