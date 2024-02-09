package com.shu.data.roomDb

import com.shu.domain.RoomRepository
import com.shu.entity.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val appDataBasePhoto: PhotoDao
) : RoomRepository {

    override fun getAllPhoto(): Flow<List<Photo>> {
        return appDataBasePhoto.getAllPhoto().map { photoDtoList ->
            photoDtoList.map { item ->
                Photo(
                    data = item.data,
                    pathPhoto = item.pathPhoto,
                    favorite = item.favorite
                )
            }
        }
    }

    override suspend fun insertPhotoRoomDataBase(photoData: String, path: String) {
        appDataBasePhoto.insert(
            PhotoDto(
                data = photoData,
                pathPhoto = path
            )
        )
    }
}