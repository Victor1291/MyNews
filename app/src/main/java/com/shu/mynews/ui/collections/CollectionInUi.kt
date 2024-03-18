package com.shu.mynews.ui.collections

import android.os.Parcelable
import com.shu.entity.icollection.Collections
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionInUi(
    override val collectionId: Int,
    override val name: String?,
    override var total: Int,
    override val icon: Int,
    override var checked: Boolean,
) : Collections,Parcelable
