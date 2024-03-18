package com.shu.domain

import com.shu.entity.icollection.IMessage
import com.shu.domain.collection.repository.CollectionRepository
import javax.inject.Inject

class CreateMessageUseCase  @Inject constructor(
    private val repository: CollectionRepository
) {

    suspend fun execute(message: IMessage) {
        repository.createMessage(message)
    }

}