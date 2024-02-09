package com.shu.domain

import com.shu.entity.Photo

class GetAllPhotoUseCase(
    private val roomRepository: RoomRepository
) {

    suspend fun execute (): List <Photo> {
        return roomRepository.getAllPhoto()
    }
}