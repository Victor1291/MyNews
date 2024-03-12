package com.shu.mynews.ui.visitor.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.mynews.R
import com.shu.mynews.databinding.TwoLineItemBinding
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.shu.mynews.ui.visitor.adapter.ViewHolderVisitor
import com.shu.mynews.ui.visitor.model.TwoStringsItem

class TwoStringsViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.two_line_item

    override fun acceptBinding(item: Any): Boolean = item is TwoStringsItem

    override fun bind(binding: ViewBinding, item: Any, clickListener: AdapterClickListenerById) {
        with((binding as TwoLineItemBinding)) {
            with((item as TwoStringsItem)) {
                text1.text = item.caption
                text2.text = item.details
            }
        }

    }
}