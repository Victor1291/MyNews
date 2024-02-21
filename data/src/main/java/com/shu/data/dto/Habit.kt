package com.shu.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shu.domain.habits.model.IHabit

@Entity(tableName = "HABITS")
@TypeConverters(value = [DaysArrayConverter::class])
data class Habit(
    @PrimaryKey
    override val id: Long,
    override val title: String,
    override val description: String?,
    override val icon: String,
    override val days: List<Int>,
    override val repeats: Int
) : IHabit