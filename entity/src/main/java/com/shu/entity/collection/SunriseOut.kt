package com.shu.entity.collection

import com.shu.entity.icollection.Sunrise

data class SunriseOut (
    override var results : ResultsOut,
    override var status  : String,
    override var tzId    : String
): Sunrise {


  companion object {
    fun NONE() = SunriseOut(
      results = ResultsOut.NONE(),
      status = "--",
      tzId = "--"
    )
  }
}