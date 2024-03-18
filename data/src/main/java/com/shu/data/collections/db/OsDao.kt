package com.shu.data.collections.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.shu.data.collections.db.CollectionsDbo
import com.shu.data.collections.db.MessageDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface OsDao {


    @Transaction
    @Query(value = "SELECT * FROM collections")
    fun getCollections(): Flow<List<CollectionsDbo>>


    @Query(value = "SELECT * FROM messages WHERE collection_id = :collectionId")
    suspend fun getMessages(collectionId: Int): List<MessageDbo>

   /* @Query("SELECT * FROM collections" +
         " INNER JOIN collections_movie WHERE kinopoisk_id = :filmId")
    suspend fun checkMovie(filmId: Int): List<CollectionsDbo>
*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCollection(collections: CollectionsDbo)


   /* @Query("DELETE FROM collections_message WHERE collection_id = :collectionId")
    suspend fun clearCollection(collectionId: Int)*/


    @Delete
    suspend fun deleteCollection(collections: CollectionsDbo)

    @Query("UPDATE collections SET total = total + 1 WHERE collection_id = :collectionId ")
    suspend fun totalAdd(collectionId: Int)

    @Query("UPDATE collections SET total = total + 1 WHERE collection_id = 1 ")
    suspend fun messageTotalAdd()

    @Query("UPDATE collections SET total = total - 1 WHERE collection_id = :collectionId ")
    suspend fun totalSub(collectionId: Int)

    @Query("UPDATE collections SET total = 0 WHERE collection_id = :collectionId ")
    suspend fun resetCollection(collectionId: Int)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(message: MessageDbo)

    @Query("UPDATE messages SET message = :message WHERE message_id = :messageId ")
    suspend fun update(message: String, messageId: Int)

   /* @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMessageInCollection(collectionsMessageDbo: CollectionsMessageDbo)*/

    @Query(value = "SELECT total FROM collections WHERE collection_id = 1")
    suspend fun getTotal() : Int


    /*  //удаляем фильм из коллекции в диалоге BotomSheet
      @Query("DELETE FROM collections_movie WHERE collection_id = :collectionId AND kinopoisk_id = :filmId")
      fun deleteMovieInDB(collectionId: Int, filmId: Int): Int*/


}