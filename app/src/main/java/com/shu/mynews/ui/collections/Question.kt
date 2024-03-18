package com.shu.mynews.ui.collections

import android.os.Parcelable
import com.shu.entity.icollection.IQuestion
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(override val questionId: Int, override val question: String): IQuestion,
    Parcelable
