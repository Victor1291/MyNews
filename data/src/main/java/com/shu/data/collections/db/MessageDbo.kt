package com.shu.data.collections.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.icollection.IMessage


@Entity(tableName = "messages")
data class MessageDbo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id")
    override val messageId: Int = 0,
    @ColumnInfo(name = "collection_id")
    override val collectionId : Int,
    @ColumnInfo(name = "message")
    override val message: String,
): IMessage {

}
