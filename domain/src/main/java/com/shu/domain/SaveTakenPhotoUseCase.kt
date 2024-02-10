package com.shu.domain

class SaveTakenPhotoUseCase(
    private val roomRepository: RoomRepository
) {

    suspend fun save(photoData: String, path : String) {
        roomRepository.insertPhotoRoomDataBase(photoData = photoData, path = path)
    }
}