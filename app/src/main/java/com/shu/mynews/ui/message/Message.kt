package com.shu.mynews.ui.message

import android.os.Parcelable
import com.shu.entity.icollection.IMessage
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    override val messageId: Int = 0,
    override val message: String,
    override val collectionId: Int,
) : IMessage, Parcelable
