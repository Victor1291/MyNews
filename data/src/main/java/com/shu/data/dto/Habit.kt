package com.shu.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shu.data.dto.DaysArrayConverter

@Entity(tableName = "HABITS")
@TypeConverters(value = [DaysArrayConverter::class])
data class Habit(
    @PrimaryKey
    val id: Long,
    val title: String,
    val description: String?,
    val icon: String,
    val days: List<Int>,
    val repeats: Int
)