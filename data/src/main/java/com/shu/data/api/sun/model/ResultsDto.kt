package com.shu.data.api.sun.model

import com.google.gson.annotations.SerializedName
import com.shu.entity.icollection.IResults


data class ResultsDto (

  @SerializedName("sunrise"                     ) override var sunrise                   : String,
  @SerializedName("sunset"                      ) override var sunset                    : String,
  @SerializedName("solar_noon"                  ) override var solarNoon                 : String,
  @SerializedName("day_length"                  ) override var dayLength                 : String,
  @SerializedName("civil_twilight_begin"        ) override var civilTwilightBegin        : String,
  @SerializedName("civil_twilight_end"          ) override var civilTwilightEnd          : String,
  @SerializedName("nautical_twilight_begin"     ) override var nauticalTwilightBegin     : String,
  @SerializedName("nautical_twilight_end"       ) override var nauticalTwilightEnd       : String,
  @SerializedName("astronomical_twilight_begin" ) override var astronomicalTwilightBegin : String,
  @SerializedName("astronomical_twilight_end"   ) override var astronomicalTwilightEnd   : String

): IResults