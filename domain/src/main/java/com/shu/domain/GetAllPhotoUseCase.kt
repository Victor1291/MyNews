package com.shu.domain

import com.shu.entity.IPhoto
import com.shu.entity.Photo
import kotlinx.coroutines.flow.Flow

class GetAllPhotoUseCase(
    private val roomRepository: RoomRepository
) {

    fun execute (): Flow<List<Photo>> {
        return roomRepository.getAllPhoto()
    }
}