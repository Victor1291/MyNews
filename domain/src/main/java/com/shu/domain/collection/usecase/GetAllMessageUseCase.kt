package com.shu.domain

import android.util.Log
import com.shu.entity.icollection.IMessage
import com.shu.domain.collection.repository.CollectionRepository
import javax.inject.Inject

class GetAllMessageUseCase @Inject constructor(
    private val repository: CollectionRepository
) {

   suspend fun execute(collectionId: Int): List<IMessage> {
       Log.d("useCase", "collectionId $collectionId")
       val list = repository.getMessages(collectionId)
       Log.d("useCase", "list $list")
        return list
    }
}