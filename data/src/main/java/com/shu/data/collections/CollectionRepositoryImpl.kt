package com.shu.data.collections

import com.shu.data.collections.db.CollectionsDbo
import com.shu.data.collections.db.MessageDbo
import com.shu.data.collections.db.OsDao
import com.shu.domain.collection.repository.CollectionRepository
import com.shu.entity.icollection.Collections
import com.shu.entity.icollection.IMessage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val osDao: OsDao
) : CollectionRepository {

    override fun getCollection(): Flow<List<Collections>> {
        return osDao.getCollections()
    }

    override fun getCollection(filmId: Int): Flow<List<Collections>> {
        return osDao.getCollections()
    }

    override suspend fun getMessages(collectionId: Int): List<IMessage> {
        return osDao.getMessages(collectionId)
    }


    override suspend fun onAdd(nameCollection: String, icon: Int) {
        //TODO при добавлении новой коллекции , фильм сразу добавляется в эту коллекцию.
        osDao.addCollection(
            CollectionsDbo(
                name = nameCollection,
                icon = icon,
                checked = true
            )
        )
    }

    override suspend fun onDelete(collections: Collections) {
        osDao.deleteCollection(collections as CollectionsDbo)
    }

   /* override suspend fun addMessageInCollection(collectionId: Int,messageId: Int) {
        osDao.addMessageInCollection(CollectionsMessageDbo(collectionId,messageId))
        osDao.totalAdd(collectionId)

    }*/


    /*override suspend fun clearCollection(collectionId: Int) {
        osDao.clearCollection(collectionId)
        osDao.resetCollection(collectionId)
    }*/

    override suspend fun createMessage(message: IMessage) {
        osDao.insert(MessageDbo(message = message.message, collectionId = message.collectionId))
        osDao.messageTotalAdd()
    }

    override suspend fun updateMessage(message: IMessage) {
        osDao.update(message = message.message, messageId = message.messageId)
    }

    override suspend fun getTotal(): Int {
       return osDao.getTotal()
    }

}