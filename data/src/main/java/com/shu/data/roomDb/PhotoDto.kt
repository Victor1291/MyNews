package com.shu.data.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.IPhoto

@Entity(tableName = "photos")
data class PhotoDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    override val data: String,
    override val pathPhoto: String,
    override val favorite: Boolean = false
) : IPhoto