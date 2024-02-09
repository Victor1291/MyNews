package com.shu.data.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photoDto")
    suspend fun getAllPhoto (): List<PhotoDto>

    @Insert
    suspend fun insert (photo: PhotoDto)
}