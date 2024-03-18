package com.shu.data.collections.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val Migration_1_2 = object: Migration(1,2) {
    override fun migrate(db: SupportSQLiteDatabase) {

        db.execSQL("DROP TABLE collections_message")
        db.execSQL("CREATE TABLE question (question_id INT AUTO_INCREMENT PRIMARY KEY, question TEXT)")

        db.execSQL("CREATE TABLE question_message(question_id INT NOT NULL, message_id INT NOT NULL," +
                "    PRIMARY KEY (question_id, message_id)," +
                "    FOREIGN KEY question_id references question (question_id) on delete cascade," +
                "    FOREIGN KEY message_id  references message  (message_id) on delete cascade" +
                ")")
    }

}