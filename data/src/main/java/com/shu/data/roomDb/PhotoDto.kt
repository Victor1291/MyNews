package com.shu.data.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.Photo

@Entity(tableName = "photoDto")
data class PhotoDto(
    @ColumnInfo(name = "path")
    override val path: String,
    @PrimaryKey
    @ColumnInfo(name = "photoDate")
    override val photoData: String
) : Photo