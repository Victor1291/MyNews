package com.shu.data.collections.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "collections_message",
    primaryKeys = ["collection_id","message_id"]
)
data class CollectionsMessageDbo(
    @ColumnInfo(name= "collection_id")
    val collectionId: Int,
    @ColumnInfo(name= "message_id")
    val messageId: Int
)

//связующая таблица. collection - movie должно быть уникальным