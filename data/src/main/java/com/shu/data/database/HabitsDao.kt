package com.shu.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shu.data.dto.Habit

@Dao
interface HabitsDao {

    @Query("SELECT * FROM HABITS")
    suspend fun getHabits(): List<Habit>

    @Insert
    suspend fun createHabit(habit: Habit)
}