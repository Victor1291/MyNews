package com.shu.domain

import com.shu.entity.icollection.Sunrise
import com.shu.domain.collection.repository.Repository
import javax.inject.Inject

class GetSunriseUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(lat: Float, lng: Float, date: String): Sunrise {
        return repository.getSunrise(lat, lng, date)
    }

}