package com.shu.mynews.ui.detail.model

import android.os.Parcelable
import com.shu.entity.modelNews.IDetailNews
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DetailNews(
   override val author: String?,
   override val image: String? ,
   override val title: String? ,
   override val description: String?,
   override val url: String?,
   override val content: String?
) : Parcelable, IDetailNews
