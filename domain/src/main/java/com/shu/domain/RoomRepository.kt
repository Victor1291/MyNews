package com.shu.domain

import com.shu.entity.Photo

interface RoomRepository {

    suspend fun getAllPhoto(): List<Photo>

    suspend fun insertPhotoRoomDataBase(photoData: String, path: String)

}