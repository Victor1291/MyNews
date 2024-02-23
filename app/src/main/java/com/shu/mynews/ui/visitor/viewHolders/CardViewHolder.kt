package com.shu.mynews.ui.visitor.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.mynews.R
import com.shu.mynews.databinding.CardItemBinding
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.squareup.picasso.Picasso
import ru.alexmaryin.recycleronvisitor.data.ui_models.CardItem
import com.shu.mynews.ui.visitor.adapter.ViewHolderVisitor

class CardViewHolder : ViewHolderVisitor {
    override val layout: Int = R.layout.card_item

    override fun acceptBinding(item: Any): Boolean = item is CardItem

    override fun bind(binding: ViewBinding, item: Any, clickListener: AdapterClickListenerById) {
        with(binding as CardItemBinding) {
            with(item as CardItem) {
                cardTitle.text =item.title

            }

            Picasso.get()
                .load(item.image)
                .into(cardBackgroundImage)
        }
    }
}