package com.shu.domain.collection.usecase

import com.shu.entity.icollection.IMessage
import com.shu.domain.collection.repository.CollectionRepository
import javax.inject.Inject

class UpdateMessageUseCase  @Inject constructor(
    private val repository: CollectionRepository
) {

    suspend fun execute(message: IMessage) {
        repository.updateMessage(message)
    }

}