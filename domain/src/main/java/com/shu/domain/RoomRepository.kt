package com.shu.domain

import com.shu.entity.Photo
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    fun getAllPhoto(): Flow<List<Photo>>

    suspend fun insertPhotoRoomDataBase(photoData: String, path: String)

}