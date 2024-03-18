package com.shu.domain.collection.repository

import com.shu.entity.icollection.Sunrise

interface Repository {

    suspend fun getSunrise(lat: Float, lng: Float, date: String): Sunrise
}