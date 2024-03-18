package com.shu.data.collections.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.icollection.Collections

@Entity(tableName = "collections")
data class CollectionsDbo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "collection_id")
    override var collectionId: Int = 0,
    @ColumnInfo(name = "name")
    override var name: String? = null,
    @ColumnInfo(name = "total")
    override var total: Int = 0,
    @ColumnInfo(name = "icon")
    override var icon: Int = 1,
    @ColumnInfo(name = "checked")
    override var checked: Boolean,
) : Collections {
}

/*
таблица с коллекциями.
 */