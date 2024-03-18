package com.shu.mynews.ui.visitor.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.mynews.R
import com.shu.mynews.databinding.CardItemBinding
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.shu.mynews.ui.visitor.adapter.ViewHolderVisitor
import com.shu.mynews.ui.visitor.model.CardItem
import com.squareup.picasso.Picasso

class CardViewHolder : ViewHolderVisitor {
    override val layout: Int = R.layout.card_item

    override fun acceptBinding(item: Any): Boolean = item is CardItem

    override fun bind(binding: ViewBinding, item: Any, clickListener: AdapterClickListenerById) {
        with(binding as CardItemBinding) {
            with(item as CardItem) {
                cardTitle.text = item.title
               // txtDiscription.text = item.description
            }

            Picasso.get()
                .load(item.image)
                .into(cardBackgroundImage)
        }
    }
}