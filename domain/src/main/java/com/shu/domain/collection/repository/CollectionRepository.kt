package com.shu.domain.collection.repository

import com.shu.entity.icollection.Collections
import com.shu.entity.icollection.IMessage
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {

    fun getCollection() : Flow<List<Collections>>

    suspend fun getMessages(collectionId: Int) : List<IMessage>

    fun getCollection(filmId: Int) : Flow<List<Collections>>

    suspend fun onAdd(nameCollection: String, icon: Int)

    suspend fun onDelete(collections: Collections)

   /* suspend fun addMessageInCollection(collectionId: Int,messageId: Int)

    suspend fun clearCollection(collectionId: Int)*/

    suspend fun createMessage(message: IMessage)

    suspend fun updateMessage(message: IMessage)

    suspend fun getTotal(): Int

}