package com.shu.data.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shu.data.dto.Completion
import com.shu.data.dto.Habit
import com.shu.data.dto.HabitNotifiers

@Database(entities = [
    PhotoDto::class,
    Completion::class,
    Habit::class,
    HabitNotifiers::class
                     ], version = 1)

abstract class AppDataBasePhoto: RoomDatabase() {

    abstract fun photoDao(): PhotoDao

}