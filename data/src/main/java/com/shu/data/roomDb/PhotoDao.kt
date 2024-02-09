package com.shu.data.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Transaction
    @Query(value = "SELECT * FROM photos")
    fun getAllPhoto(): Flow<List<PhotoDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: PhotoDto)

    @Query("DELETE FROM photos WHERE id = :id")
    suspend fun delete(id: Int)
}