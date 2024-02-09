package com.shu.domain

class SaveTakenPhotoUseCase(
    private val roomRepository: RoomRepository
) {

    suspend fun save(path: String, photoData: String) {
        roomRepository.insertPhotoRoomDataBase(photoData = photoData, path = path)
    }
}