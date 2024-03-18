package com.shu.data.api.sun

import com.shu.data.api.sun.api.ApiSun
import com.shu.entity.icollection.Sunrise
import com.shu.domain.collection.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiSun
): Repository {
    override suspend fun getSunrise(lat: Float, lng: Float, date: String): Sunrise {
        return api.getSunrise(lat,lng,date)
    }
}