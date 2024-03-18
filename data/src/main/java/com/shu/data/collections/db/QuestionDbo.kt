package com.shu.data.collections.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.icollection.IQuestion


@Entity(tableName = "question")
data class QuestionDbo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    override val questionId: Int = 0,
    @ColumnInfo(name = "question")
    override val question: String,
): IQuestion {

}