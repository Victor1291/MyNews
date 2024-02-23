package com.shu.mynews.ui.habits

import androidx.annotation.LayoutRes

interface MainListItemType {

    @LayoutRes
    fun getItemType(): Int

    fun size(): Int
}