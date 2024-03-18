package com.shu.data.collections.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "question_message",
    primaryKeys = ["question_id","message_id"]
)
data class QuestionMessageDbo(
    @ColumnInfo(name= "question_id")
    val questionId: Int,
    @ColumnInfo(name= "message_id")
    val messageId: Int
)
