package com.shu.data.dto

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time
}