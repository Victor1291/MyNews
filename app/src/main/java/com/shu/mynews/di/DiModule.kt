package com.shu.mynews.di

import dagger.Module
import dagger.Provides
import com.shu.mynews.ui.visitor.adapter.ItemTypes
import com.shu.mynews.ui.visitor.adapter.ViewHoldersManager
import com.shu.mynews.ui.visitor.adapter.ViewHoldersManagerImpl
import com.shu.mynews.ui.visitor.viewHolders.CardViewHolder
import com.shu.mynews.ui.visitor.viewHolders.HeaderViewHolder
import com.shu.mynews.ui.visitor.viewHolders.OneLine2ViewHolder
import com.shu.mynews.ui.visitor.viewHolders.TwoStringsViewHolder

@Module
object DiModule {

    @Provides
    fun provideAdaptersManager(): ViewHoldersManager = ViewHoldersManagerImpl().apply {
        registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
        registerViewHolder(ItemTypes.ONE_LINE_STRINGS, OneLine2ViewHolder())
        registerViewHolder(ItemTypes.TWO_STRINGS, TwoStringsViewHolder())
        registerViewHolder(ItemTypes.CARD, CardViewHolder())
    }
}