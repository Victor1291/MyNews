package com.shu.data.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shu.data.collections.db.CollectionsDbo
import com.shu.data.collections.db.MessageDbo
import com.shu.data.collections.db.OsDao
import com.shu.data.collections.db.QuestionDbo
import com.shu.data.collections.db.QuestionMessageDbo
import com.shu.data.database.HabitsDao
import com.shu.data.dto.Completion
import com.shu.data.dto.Habit
import com.shu.data.dto.HabitNotifiers

@Database(
    entities = [
        PhotoDto::class,
        Completion::class,
        Habit::class,
        HabitNotifiers::class,
        MessageDbo::class,
        QuestionDbo::class,
        CollectionsDbo::class,
        QuestionMessageDbo::class,
    ], version = 1
)

abstract class AppDataBasePhoto : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    abstract fun habitsDao(): HabitsDao

    abstract fun osDao(): OsDao

}