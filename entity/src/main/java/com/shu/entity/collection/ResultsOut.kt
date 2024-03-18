package com.shu.entity.collection

import com.shu.entity.icollection.IResults

data class ResultsOut (

  override var sunrise                   : String,
  override var sunset                    : String,
  override var solarNoon                 : String,
  override var dayLength                 : String,
  override var civilTwilightBegin        : String,
  override var civilTwilightEnd          : String,
  override var nauticalTwilightBegin     : String,
  override var nauticalTwilightEnd       : String,
  override var astronomicalTwilightBegin : String,
  override var astronomicalTwilightEnd   : String

): IResults {
  companion object {
    fun NONE() = ResultsOut(
      sunrise = "--",
              sunset = "--",
              solarNoon = "--",
              dayLength = "--",
              civilTwilightBegin = "--",
              civilTwilightEnd = "--",
              nauticalTwilightBegin = "--",
              nauticalTwilightEnd = "--",
              astronomicalTwilightBegin = "--",
              astronomicalTwilightEnd = "--",
    )
  }
}