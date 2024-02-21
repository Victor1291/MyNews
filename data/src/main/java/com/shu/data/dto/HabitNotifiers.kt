package com.shu.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.shu.data.dto.Habit

@Entity(
    tableName = "NOTIFIERS",
    foreignKeys = [ForeignKey(
        entity = Habit::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("habit_id")
    )]
)
data class HabitNotifiers(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "habit_id") val habitId: Int,
    val timestamp: Long
)