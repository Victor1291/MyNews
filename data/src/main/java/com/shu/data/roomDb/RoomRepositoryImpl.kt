package com.shu.data.roomDb

import com.shu.domain.RoomRepository
import com.shu.entity.Photo

class RoomRepositoryImpl(
    private val appDataBasePhoto: PhotoDao
): RoomRepository {

    override suspend fun getAllPhoto(): List<Photo> {
        return appDataBasePhoto.getAllPhoto()
    }

    override suspend fun insertPhotoRoomDataBase(photoData: String, path: String) {
        appDataBasePhoto.insert(
            PhotoDto(
                photoData = photoData,
                path = path
            )
        )
    }
}