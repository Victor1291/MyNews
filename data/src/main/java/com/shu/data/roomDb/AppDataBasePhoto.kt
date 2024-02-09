package com.shu.data.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoDto::class], version = 1)

abstract class AppDataBasePhoto: RoomDatabase() {

    abstract fun photoDao(): PhotoDao

}